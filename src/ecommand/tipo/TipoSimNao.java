package ecommand.tipo;

public enum TipoSimNao {

    SIM(1, "S"),
    NAO(2, "N");

    private final int id;
    private final String flag;

    private TipoSimNao(int id, String flag) {
        this.id = id;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public String getFlag() {
        return flag;
    }

}
