package ecommand.tipo;

public enum TipoRegimeEspecialTributacaoISSQN {

    MICROEMPRESA_MUNICIPAL(1),
    ESTIMATIVA(2),
    SOCIEDADE_PROFISSIONAIS(3),
    COOPERATIVA(4),
    MICROEMPRESARIO_INDIVIDUAL_MEI(5);

    private final int id;

    private TipoRegimeEspecialTributacaoISSQN(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
