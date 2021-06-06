package ecommand.model.cadastro;

import java.util.ArrayList;

public class ReceitaVO {

    public int id = 0;
    public String descricao = "";
    public double custo = 0;
    public double margem = 0;
    public double precoVenda = 0;
    public String observacao = "";
    ArrayList<ProdutoVO> vItens = new ArrayList();

}
