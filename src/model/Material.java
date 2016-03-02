package model;

/**
 * Representa um material através do id e da designação.
 */
public class Material {

    /**
     * ID do material.
     */
    private int id;

    /**
     * Designação do material.
     */
    private String designacao;

    /**
     * Constrói uma instância de um material recebendo um id e uma descrição por
     * parametro.
     *
     * @param id Id do material.
     * @param designacao Designação do material.
     */
    public Material(int id, String designacao) {
        this.id = id;
        this.designacao = designacao;
    }

    /**
     * Devolve o ID do país.
     *
     * @return ID do país.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Devolve a informação do material.
     *
     * @return Informação do material.
     */
    @Override
    public String toString() {
        return this.designacao;
    }

    /**
     * Verifica se dois materiais são iguais.
     *
     * @param outroObjeto Objeto utilizado na comparação.
     * @return Verdadeiro se os dois materiais forem iguais e falso caso não
     * sejam.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }

        if (outroObjeto == null || this.getClass() != outroObjeto.getClass()) {
            return false;
        }

        Material outroMaterial = (Material) outroObjeto;

        return this.id == outroMaterial.id;
    }

}
