package model;

/**
 * Representa um pais através de um id e um nome.
 */
public class Pais {

    /**
     * Id do pais.
     */
    private int id;

    /**
     * Nome do pais.
     */
    private String nome;

    /**
     * Constrói uma instância de um pais recebendo um id e um nome por
     * parametro.
     *
     * @param id Id do pais.
     * @param nome Nome do pais.
     */
    public Pais(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * Devolve o id do pais.
     *
     * @return Id do pais.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Imprime a informação de um pais.
     *
     * @return Informação de um pais.
     */
    @Override
    public String toString() {
        return this.nome;
    }

}
