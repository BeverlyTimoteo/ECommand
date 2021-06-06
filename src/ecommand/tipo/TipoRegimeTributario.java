package ecommand.tipo;

public enum TipoRegimeTributario {

    SIMPLES_NACIONAL(1),
    REGIME_NORMAL(3);

    private final int id;

    private TipoRegimeTributario(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
