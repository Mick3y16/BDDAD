package model;

/**
 * Representa uma cidade através de um id e um nome.
 */
public class Cidade {

    /**
     * Id da cidade.
     */
    private int id;

    /**
     * Nome da cidade.
     */
    private String nome;

    /**
     * Constrói uma instância de cidade recebendo um nome por parametro.
     *
     * @param id Id da cidade.
     * @param nome Nome da cidade.
     */
    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * Devolve o id da cidade.
     *
     * @return Id da cidade.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Imprime a informação de uma cidade.
     *
     * @return informação de uma cidade.
     */
    @Override
    public String toString() {
        return this.nome;
    }
}
