package ecommand.dao.cadastro;

import ecommand.model.cadastro.FornecedorFiltroConsultaVO;
import ecommand.model.cadastro.FornecedorVO;
import ecommandtools.connection.Conexao;
import ecommandtools.connection.Sql;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void deletar(FornecedorVO fornecedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FornecedorVO> consultar(FornecedorFiltroConsultaVO oFiltro) throws Exception {
        try (Statement stm = Conexao.createStatement()) {
            Sql sql = new Sql("select id, razao, fantasia, cnpj, id_tipopessoa from fornecedor");
            sql.add("where 1 = 1");

            if (oFiltro.id != -1) {
                sql.add("and id = " + oFiltro.id);
            }

            if (!oFiltro.nome.trim().isEmpty()) {
                sql.add("and (razao ilike '%" + oFiltro.nome + "%'");
                sql.add("or fantasia ilike '%" + oFiltro.nome + "%')");
            }

            if (oFiltro.cnpj > 0) {
                sql.add("and cnpj = " + oFiltro.cnpj);
            }

            sql.add("order by id");

            try (ResultSet rst = stm.executeQuery(sql.getSql())) {
                List<FornecedorVO> vFornecedor = new ArrayList<>();
                while (rst.next()) {
                    FornecedorVO fornecedor = new FornecedorVO();
                    fornecedor.id = rst.getInt("id");
                    fornecedor.razao = rst.getString("descricao");
                    fornecedor.fantasia = rst.getString("descricao");
                    fornecedor.cnpj = rst.getLong("cnpj");

                    vFornecedor.add(fornecedor);
                }

                return vFornecedor;
            }
        }
    }

}
