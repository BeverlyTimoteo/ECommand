package ecommand.dao.cadastro;

import ecommand.model.cadastro.ProdutoCategoriaFiltroConsultaVO;
import ecommand.model.cadastro.ProdutoCategoriaVO;
import ecommandtools.connection.Conexao;
import ecommandtools.connection.Database;
import ecommandtools.connection.Sql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoCategoriaDAO {

    public List<ProdutoCategoriaVO> consultar(ProdutoCategoriaFiltroConsultaVO oFiltro) throws Exception {
        List<ProdutoCategoriaVO> vCategoria = new ArrayList<>();

        try (Statement stm = Conexao.createStatement()) {
            Sql sql = new Sql("select * from produtocategoria");
            sql.add("where 1 = 1");

            if (oFiltro.id != -1) {
                sql.add("and id = " + oFiltro.id);
            }

            if (!oFiltro.descricao.trim().isEmpty()) {
                sql.add("and descricao ilike '%" + oFiltro.descricao + "%'");
            }

            sql.add("order by id");

            try (ResultSet rst = stm.executeQuery(sql.getSql())) {
                while (rst.next()) {
                    ProdutoCategoriaVO categoria = new ProdutoCategoriaVO();
                    categoria.id = rst.getInt("id");
                    categoria.descricao = rst.getString("descricao");
                    categoria.imagem = rst.getString("imagem");

                    vCategoria.add(categoria);
                }
            }
        }

        return vCategoria;
    }

    public void salvar(ProdutoCategoriaVO categoria) throws Exception {
        Statement stm = null;
        ResultSet rst = null;
        Sql sql = null;
        
        try {
            Conexao.begin();

            stm = Conexao.createStatement();

            rst = stm.executeQuery("select id from produtocategoria where id = " + categoria.id);

            sql = new Sql();

            if (rst.next()) {
                sql.add("update produtocategoria set ");
                sql.add("descricao = ?, imagem = ? where id = ?");

                Database.execute(sql.getSql(), categoria.descricao, categoria.imagem, categoria.id);

            } else {
                sql.add("insert into produtocategoria(descricao, imagem) values (?, ?);");

                Database.execute(sql.getSql(), categoria.descricao, categoria.imagem);

                rst = stm.executeQuery("select currval('produtocategoria_id_seq') as id");
                rst.next();

                categoria.id = rst.getInt("id");
            }

            rst.close();
            stm.close();

            Conexao.commit();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

    public ProdutoCategoriaVO carregar(int id) throws Exception {
        ProdutoCategoriaVO categoria = new ProdutoCategoriaVO();

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement("select * from produtocategoria where id = ?")) {
            ps.setInt(1, id);

            ResultSet rst = ps.executeQuery();
            if (rst.next()) {
                categoria.id = rst.getInt("id");
                categoria.descricao = rst.getString("descricao");
                categoria.imagem = rst.getString("imagem");
            }
        }

        return categoria;
    }

    public void deletar(ProdutoCategoriaVO categoria) throws Exception {
        try {
            Conexao.begin();

            Database.execute("delete from produtocategoria where id = " + categoria.id);

            Conexao.commit();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

}
