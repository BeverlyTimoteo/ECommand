package ecommand.dao.cadastro;

import ecommand.model.cadastro.ProdutoFiltroConsultaVO;
import ecommand.model.cadastro.ProdutoVO;
import ecommand.tipo.SituacaoCadastro;
import ecommandtools.connection.Conexao;
import ecommandtools.connection.Database;
import ecommandtools.connection.Sql;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public ProdutoVO carregar(int p_id) throws Exception {
        Statement stm = null;
        ResultSet rst = null;

        stm = Conexao.createStatement();

        rst = stm.executeQuery("SELECT * FROM produto WHERE id = " + p_id);

        ProdutoVO oProduto = new ProdutoVO();

        if (rst.next()) {
            oProduto.id = rst.getInt("id");
            oProduto.descricao = rst.getString("descricao");
            oProduto.idUnidadeMedida = rst.getInt("id_medida");
            oProduto.idSituacaoCadastro = rst.getInt("id_situacaocadastro");
            oProduto.custo = rst.getDouble("custo");
            oProduto.margem = rst.getDouble("margem");
            oProduto.precoVenda = rst.getDouble("precovenda");
            oProduto.adicional = rst.getBoolean("adicional");
            oProduto.comercializado = rst.getBoolean("comercializado");
            oProduto.controlaEstoque = rst.getBoolean("controlaestoque");
            oProduto.insumo = rst.getBoolean("insumo");
            oProduto.idCategoria = rst.getInt("id_categoria");
            oProduto.observacao = rst.getString("observacao");
        }

        return oProduto;

    }

    public void salvar(ProdutoVO p_produto) throws Exception {
        Statement stm = null;
        ResultSet rst = null;
        Sql sql;

        try {
            Conexao.begin();

            stm = Conexao.createStatement();

            rst = stm.executeQuery("SELECT id FROM produto WHERE id = " + p_produto.id);

            if (rst.next()) {
                sql = new Sql("UPDATE produto SET");
                sql.add("descricao = '" + p_produto.descricao + "',");
                sql.add("id_medida = " + p_produto.idUnidadeMedida + ",");
                sql.add("custo = " + p_produto.custo + ",");
                sql.add("margem = " + p_produto.margem + ",");
                sql.add("precovenda = " + p_produto.precoVenda + ",");
                sql.add("id_situacaocadastro = " + p_produto.idSituacaoCadastro + ",");
                sql.add("insumo = " + p_produto.insumo + ",");
                sql.add("comercializado = " + p_produto.comercializado + ",");
                sql.add("controlaestoque = " + p_produto.controlaEstoque + ",");
                sql.add("adicional = " + p_produto.adicional + ",");
                sql.add("id_categoria = " + p_produto.idCategoria + ",");
                sql.add("observacao = '" + p_produto.observacao + "'");
                sql.add("WHERE id = " + p_produto.id);

                Database.execute(sql);

            } else {
                sql = new Sql("INSERT INTO produto (");
                sql.add("descricao,");
                sql.add("id_medida,");
                sql.add("custo,");
                sql.add("margem,");
                sql.add("precovenda,");
                sql.add("id_situacaocadastro,");
                sql.add("insumo,");
                sql.add("comercializado,");
                sql.add("controlaestoque,");
                sql.add("adicional,");
                sql.add("id_categoria,");
                sql.add("observacao)");
                sql.add("VALUES ('");
                sql.add(p_produto.descricao + "' ,");
                sql.add(p_produto.idUnidadeMedida + ",");
                sql.add(p_produto.custo + ",");
                sql.add(p_produto.margem + ",");
                sql.add(p_produto.precoVenda + ",");
                sql.add(p_produto.idSituacaoCadastro + ",");
                sql.add(p_produto.insumo + ",");
                sql.add(p_produto.comercializado + ",");
                sql.add(p_produto.controlaEstoque + ",");
                sql.add(p_produto.adicional + ", ");
                sql.add(p_produto.idCategoria + ", '");
                sql.add(p_produto.observacao + "' )");

                Database.execute(sql);

                rst = stm.executeQuery("SELECT CURRVAL('produto_id_seq') as id");
                rst.next();

                p_produto.id = rst.getInt("id");

                Conexao.commit();

                rst.close();
                stm.close();

            }

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

    public void deletar(ProdutoVO p_Produto) throws Exception {
        Sql sql;

        try {
            Conexao.begin();

            sql = new Sql("UPDATE produto SET");
            sql.add("id_situacaocadastro = " + SituacaoCadastro.EXCLUIDO.getId());
            sql.add("WHERE id = " + p_Produto.id);

            Database.execute(sql);

            Conexao.commit();

        } catch (Exception e) {
            Conexao.rollback();
            throw e;
        }
    }

    public List<ProdutoVO> consultar(ProdutoFiltroConsultaVO p_filtro) throws Exception {
        Statement stm = null;
        ResultSet rst = null;
        Sql sql;

        stm = Conexao.createStatement();

        sql = new Sql("SELECT prod.*, sit.descricao AS situacaocadastro, cat.descricao AS categoria");
        sql.add("FROM produto AS prod");
        sql.add("INNER JOIN situacaocadastro AS sit ON sit.id = prod.id_situacaocadastro");
        sql.add("INNER JOIN produtocategoria AS cat ON cat.id = prod.id_categoria");
        sql.add("WHERE 1 = 1");

        if (p_filtro.id != -1) {
            sql.add(" AND prod.id = " + p_filtro.id);
        }

        if (p_filtro.idSituacaoCadastro != -1) {
            sql.add("AND prod.id_situacaocadastro = " + p_filtro.idSituacaoCadastro);
        }

        if (!p_filtro.descricao.isEmpty()) {
            sql.add("AND descricao ILIKE (%" + p_filtro.descricao + "%)");
        }

        sql.add("ORDER BY prod.descricao");

        rst = stm.executeQuery(sql.getSql());

        List<ProdutoVO> arrayProduto = new ArrayList();

        while (rst.next()) {
            ProdutoVO oProduto = new ProdutoVO();

            oProduto.id = rst.getInt("id");
            oProduto.descricao = rst.getString("descricao");
            oProduto.custo = rst.getDouble("custo");
            oProduto.margem = rst.getDouble("margem");
            oProduto.precoVenda = rst.getDouble("precovenda");
            oProduto.adicional = rst.getBoolean("adicional");
            oProduto.comercializado = rst.getBoolean("comercializado");
            oProduto.controlaEstoque = rst.getBoolean("controlaestoque");
            oProduto.insumo = rst.getBoolean("insumo");
            oProduto.observacao = rst.getString("observacao");
            oProduto.idSituacaoCadastro = rst.getInt("id_situacaocadastro");
            oProduto.categoria = rst.getString("categoria");
            oProduto.situacaoCadastro = rst.getString("situacaocadastro");

            arrayProduto.add(oProduto);

        }

        return arrayProduto;

    }
}
