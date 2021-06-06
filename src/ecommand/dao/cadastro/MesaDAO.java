package ecommand.dao.cadastro;

import ecommand.model.cadastro.MesaFiltroConsultaVO;
import ecommand.model.cadastro.MesaVO;
import ecommandtools.connection.Conexao;
import ecommandtools.connection.Database;
import ecommandtools.connection.Sql;
import ecommandtools.exception.ExceptionCustom;
import ecommandtools.utils.Formatacao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO {

    public List<MesaVO> consultar(MesaFiltroConsultaVO oFiltro) throws Exception {
        try (Statement stm = Conexao.createStatement()) {
            Sql sql = new Sql("SELECT * FROM mesa");
            sql.add("WHERE 1 = 1");

            if (oFiltro.id != -1) {
                sql.add("AND id = " + oFiltro.id);
            }

            if (!oFiltro.descricao.trim().isEmpty()) {
                sql.add("AND descricao ilike '%" + oFiltro.descricao + "%'");
            }
            
            if (!oFiltro.identificacao.trim().isEmpty()) {
                sql.add("AND identificacao ilike '%" + oFiltro.identificacao + "%'");
            }

            sql.add("ORDER BY id");

            List<MesaVO> vMesa = new ArrayList<>();
            
            try (ResultSet rst = stm.executeQuery(sql.getSql())) {
                while (rst.next()) {
                    MesaVO oMesa = new MesaVO();
                    oMesa.id = rst.getInt("id");
                    oMesa.descricao = rst.getString("descricao");
                    oMesa.identificacao = rst.getString("identificacao");

                    vMesa.add(oMesa);
                }
            }
            
            return vMesa;
        }
    }

    public void salvar(MesaVO oMesa) throws Exception {
        Statement stm = null;
        ResultSet rst = null;
        Sql sql = null;

        try {
            Conexao.begin();

            stm = Conexao.createStatement();

            rst = stm.executeQuery("SELECT id FROM mesa WHERE id = " + oMesa.id);

            sql = new Sql();

            if (rst.next()) {
                sql.add("UPDATE mesa SET ");
                sql.add("descricao = '" + oMesa.descricao + "',");
                sql.add("identificacao = '" + oMesa.identificacao + "',");
                sql.add("observacao = '" + oMesa.observacao + "'");
                sql.add("WHERE id = " + oMesa.id);

                Database.execute(sql.getSql());

            } else {
                sql.add("INSERT INTO mesa(descricao, identificacao, observacao)");
                sql.add("VALUES (");
                sql.add("'" + oMesa.descricao + "', ");
                sql.add("'" + oMesa.identificacao + "', ");
                sql.add("'" + oMesa.observacao + "'");
                sql.add(");");

                Database.execute(sql.getSql());

                rst = stm.executeQuery("SELECT CURRVAL('mesa_id_seq') AS id");
                rst.next();

                oMesa.id = rst.getInt("id");
            }

            rst.close();
            stm.close();

            Conexao.commit();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

    public MesaVO carregar(int id) throws Exception {
        try (Statement stm = Conexao.createStatement();
                ResultSet rst = stm.executeQuery("SELECT * FROM mesa WHERE id = " + id)) {
            if (!rst.next()) {
                throw new ExceptionCustom("Mesa " + Formatacao.numberLeft(id, 3) + " não encontrado!");
            }

            MesaVO oMesa = new MesaVO();
            oMesa.id = rst.getInt("id");
            oMesa.descricao = rst.getString("descricao");
            oMesa.observacao = rst.getString("observacao");
            oMesa.identificacao = rst.getString("identificacao");

            return oMesa;
        }
    }

    public void excluir(MesaVO oMesa) throws Exception {
        try {
            Conexao.begin();

            Database.execute("DELETE FROM mesa where id = " + oMesa.id);

            Conexao.commit();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

}
