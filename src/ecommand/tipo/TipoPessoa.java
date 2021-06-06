package ecommand.tipo;

public enum TipoPessoa {

    FISICA(1),
    JURIDICA(2);

    private final int id;

    private TipoPessoa(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
