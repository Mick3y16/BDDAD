package model;

/**
 * Representa um sistema de saúde através de um id e uma designação.
 */
public class SistemaSaude {

    /**
     * Id do sistema de saúde.
     */
    private int id;

    /**
     * Designação do sistema de saúde.
     */
    private String designacao;

    /**
     * Constrói uma instância de sistema de saúde recebendo um id e uma
     * designação por parametro.
     *
     * @param id Id do sistema de saúde.
     * @param designacao Desginação do sistema de saúde.
     */
    public SistemaSaude(int id, String designacao) {
        this.id = id;
        this.designacao = designacao;
    }

    /**
     * Devolve o id do sistema de saúde.
     *
     * @return Id do sistema de saúde.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Imprime a informação de um sistema de saúde.
     *
     * @return informação de um sistema de saúde.
     */
    @Override
    public String toString() {
        return this.designacao;
    }
}
