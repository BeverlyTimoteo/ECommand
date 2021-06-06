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

public class ProdutoDAO {

    public ProdutoVO carregar(int p_id) throws Exception {
        Statement stm = null;
        ResultSet rst = null;

        stm = Conexao.createStatement();

        rst = stm.executeQuery("SELECT * FROM produto WHERE id = " + p_id);

        ProdutoVO oProduto = new ProdutoVO();

        if (rst.next()) {
            oProduto.id = rst.getInt("id");
            oProduto.descricao = rst.getString("descricao");
            oProduto.idUnidadeMedida = rst.getInt("id_unidademedida");
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

    public void salvar(ProdutoVO produto) throws Exception {
        Statement stm = null;
        ResultSet rst = null;

        try {
            Conexao.begin();

            stm = Conexao.createStatement();

            rst = stm.executeQuery("SELECT id FROM produto WHERE id = " + produto.id);

            Sql sql;

            if (rst.next()) {
                sql = new Sql("UPDATE produto SET");
                sql.add("descricao = '" + produto.descricao + "',");
                sql.add("id_unidademedida = " + produto.idUnidadeMedida + ",");
                sql.add("custo = " + produto.custo + ",");
                sql.add("margem = " + produto.margem + ",");
                sql.add("precovenda = " + produto.precoVenda + ",");
                sql.add("id_situacaocadastro = " + produto.idSituacaoCadastro + ",");
                sql.add("insumo = " + produto.insumo + ",");
                sql.add("comercializado = " + produto.comercializado + ",");
                sql.add("controlaestoque = " + produto.controlaEstoque + ",");
                sql.add("adicional = " + produto.adicional + ",");
                sql.add("id_categoria = " + produto.idCategoria + ",");
                sql.add("observacao = '" + produto.observacao + "'");
                sql.add("WHERE id = " + produto.id);

                Database.execute(sql);

            } else {
                sql = new Sql("INSERT INTO produto (");
                sql.add("descricao, id_unidademedida, custo, margem, precovenda,");
                sql.add("id_situacaocadastro, insumo, comercializado, controlaestoque,");
                sql.add("adicional, id_categoria, observacao");
                sql.add(") VALUES ('");
                sql.add(produto.descricao + "' ,");
                sql.add(produto.idUnidadeMedida + ",");
                sql.add(produto.custo + ",");
                sql.add(produto.margem + ",");
                sql.add(produto.precoVenda + ",");
                sql.add(produto.idSituacaoCadastro + ",");
                sql.add(produto.insumo + ",");
                sql.add(produto.comercializado + ",");
                sql.add(produto.controlaEstoque + ",");
                sql.add(produto.adicional + ", ");
                sql.add(produto.idCategoria + ", '");
                sql.add(produto.observacao + "' )");

                Database.execute(sql);

                rst = stm.executeQuery("SELECT CURRVAL('produto_id_seq') as id");
                rst.next();

                produto.id = rst.getInt("id");

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

        List<ProdutoVO> vProduto = new ArrayList();

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

            vProduto.add(oProduto);
        }

        return vProduto;
    }
}
