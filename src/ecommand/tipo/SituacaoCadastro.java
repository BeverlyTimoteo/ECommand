package ecommand.tipo;

public enum SituacaoCadastro {

    TODOS(-1, "TODOS"),
    ATIVO(1, "ATIVO"),
    EXCLUIDO(2, "EXCLUIDO");

    private final int id;
    private final String descricao;

    private SituacaoCadastro(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
