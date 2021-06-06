package ecommand.tipo;

public enum UnidadeMedida {

    KG(1, "KG"),
    PC(2, "PC"),
    UN(3, "UN"),
    LT(4, "LT"),
    ML(5, "ML");

    private final int id;
    private final String descricao;

    private UnidadeMedida(int id, String descricao) {
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
