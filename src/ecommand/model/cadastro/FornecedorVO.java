package ecommand.model.cadastro;

import ecommand.tipo.TipoPessoa;
import ecommand.tipo.TipoRegimeEspecialTributacaoISSQN;
import ecommand.tipo.TipoRegimeTributario;
import ecommand.tipo.TipoSimNao;

public class FornecedorVO {

    public int id = 0;
    public String razao = "";
    public String fantasia = "";
    public long cnpj = 0;
    public String inscricaoEstadual = "";
    public String inscricaoMunicipal = "";
    public String logradouro = "";
    public String numero = "";
    public String cep = "";
    public String complemento = "";
    public int idBairro = 0;
    public int idMunicipio = 0;
    public int idRegimeTributario = TipoRegimeTributario.SIMPLES_NACIONAL.getId();
    public int idRegimeEspecialTributacaoISSQN = TipoRegimeEspecialTributacaoISSQN.MICROEMPRESARIO_INDIVIDUAL_MEI.getId();
    public int idIndicadorRateioDescontoISSQN = TipoSimNao.NAO.getId();
    public int idTipoPessoa = TipoPessoa.JURIDICA.getId();

}
