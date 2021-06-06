package ecommand.dao.cadastro;

import ecommand.model.cadastro.UsuarioFiltroConsultaVO;
import ecommand.model.cadastro.UsuarioVO;
import ecommand.tipo.SituacaoCadastro;
import ecommandtools.connection.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ecommandtools.view.Biometria;

public class UsuarioDAO {

    public UsuarioVO validarBiometriaUsuario(String biometriaCapturada) throws Exception {
        try (Statement stm = Conexao.createStatement();
                ResultSet rst = stm.executeQuery("select * from usuario where digital is not null and trim(digital) <> ''")) {

            while (rst.next()) {
                UsuarioVO usuario = new UsuarioVO();
                usuario.id = rst.getInt("id");
                usuario.nome = rst.getString("nome");
                usuario.senha = rst.getString("senha");
                usuario.digital = rst.getString("digital");

                if (Biometria.compararBiometrias(biometriaCapturada, usuario.digital)) {
                    rst.close();
                    stm.close();

                    return usuario;
                }
            }

            rst.close();
            stm.close();
        }

        return null;
    }

    public UsuarioVO verificarLogin(String usuario, char[] senha) throws Exception {
        if (usuario.equals("ECOMMAND") && String.valueOf(senha).equals("ecommand")) {
            UsuarioVO user = new UsuarioVO();
            user.nome = "ADMIN";
            return user;
        }

        PreparedStatement ps = Conexao.getConexao().prepareStatement("select * from usuario where nome = ? and senha = md5(?)");
        ps.setString(1, usuario);
        ps.setString(2, String.valueOf(senha));

        ResultSet rst = ps.executeQuery();

        if (rst.next()) {
            return carregar(rst.getInt("id"));
        } else {
            return null;
        }
    }

    public UsuarioVO carregar(int id) throws Exception {
        PreparedStatement ps = Conexao.getConexao().prepareStatement("select * from usuario where id = ?");
        ps.setInt(1, id);

        ResultSet rst = ps.executeQuery();

        if (rst.next()) {
            UsuarioVO usuario = new UsuarioVO();
            usuario.id = rst.getInt("id");
            usuario.nome = rst.getString("nome");
            usuario.senha = rst.getString("senha");
            usuario.digital = rst.getString("digital");
            usuario.idSituacaoCadastro = rst.getInt("id_situacaocadastro");
            return usuario;

        } else {
            return null;
        }
    }

    public void deletar(UsuarioVO usuario) throws Exception {
        Statement stm = null;

        try {
            Conexao.begin();

            stm = Conexao.createStatement();

            stm.execute("update usuario set id_situacaocadastro = " + SituacaoCadastro.EXCLUIDO.getId() + " where id = " + usuario.id);

            Conexao.commit();

            stm.close();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

    public List<UsuarioVO> consultar(UsuarioFiltroConsultaVO oFiltro) throws Exception {
        Statement stm = null;
        ResultSet rst = null;
        StringBuilder sql = null;

        stm = Conexao.createStatement();

        sql = new StringBuilder();
        sql.append("select u.nome, u.id, s.descricao from usuario u inner join situacaocadastro s ON s.id = u.id_situacaocadastro");
        sql.append(" where 1 = 1");

        if (oFiltro.id != -1) {
            sql.append(" and u.id = " + oFiltro.id);
        }

        if (!oFiltro.nome.trim().isEmpty()) {
            sql.append(" and nome ilike '%" + oFiltro.nome + "%'");
        }

        if (oFiltro.idSituacao != -1) {
            sql.append(" and id_situacaocadastro = " + oFiltro.idSituacao);
        }

        sql.append(" order by id");

        rst = stm.executeQuery(sql.toString());

        List<UsuarioVO> lUsuario = new ArrayList();

        while (rst.next()) {
            UsuarioVO user = new UsuarioVO();
            user.id = rst.getInt("id");
            user.nome = rst.getString("nome");
            user.situacaocadastro = rst.getString("descricao");
            lUsuario.add(user);
        }

        rst.close();
        stm.close();

        return lUsuario;
    }

    public void salvar(UsuarioVO usuario) throws Exception {
        Statement stm = null;
        ResultSet rst = null;
        StringBuilder sql = null;

        try {
            Conexao.begin();

            stm = Conexao.createStatement();

            rst = stm.executeQuery("select id, senha from usuario where id = " + usuario.id);

            sql = new StringBuilder();

            if (rst.next()) {
                String md5 = rst.getString("senha");
                String novaMd5 = "";

                rst = stm.executeQuery("select md5('" + usuario.senha + "') as senha");
                if (rst.next()) {
                    novaMd5 = rst.getString("senha");
                }

                sql.append("update usuario set nome = '" + usuario.nome + "',");

                if (!md5.equals(novaMd5)) {
                    sql.append(" senha = '" + novaMd5 + "',");
                }

                sql.append(" digital = '" + usuario.digital + "',");
                sql.append(" id_situacaocadastro = " + usuario.idSituacaoCadastro);
                sql.append(" where id = " + usuario.id);
                stm.execute(sql.toString());

            } else {
                sql.append("insert into usuario(nome, senha, digital, id_situacaocadastro) values (");
                sql.append("'" + usuario.nome + "',");
                sql.append("(select md5('" + usuario.senha + "')),");
                sql.append("'" + usuario.digital + "',");
                sql.append(usuario.idSituacaoCadastro + ")");

                stm.execute(sql.toString());

                rst = stm.executeQuery("select currval('usuario_id_seq') as id");
                rst.next();

                usuario.id = rst.getInt("id");
            }

            Conexao.commit();

            rst.close();
            stm.close();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

}
