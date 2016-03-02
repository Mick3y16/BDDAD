package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um serviço através de um id, uma designação e um valor.
 */
public class Servico {

    /**
     * ID do Serviço.
     */
    private int id;

    /**
     * Designação do Serviço.
     */
    private String designacao;

    /**
     * Valor do Serviço.
     */
    private double valor;

    private List<Material> listaMaterial;

    /**
     * Constrói uma instância de Serviço recebendo por parâmetro um ID, uma
     * designação e um valor.
     *
     * @param id ID do serviço.
     * @param designacao Designação do Serviço.
     * @param valor Valor do Serviço.
     */
    public Servico(int id, String designacao, double valor) {
        this.id = id;
        this.designacao = designacao;
        this.valor = valor;
        this.listaMaterial = new ArrayList<>();
    }

    /**
     * Devolve o ID do serviço.
     *
     * @return ID do serviço.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Devolve a lista de material usada no serviço.
     * 
     * @return Lista de material usada no serviço.
     */
    public List<Material> getListaMaterial() {
        return this.listaMaterial;
    }

    /**
     * Informação do Serviço.
     *
     * @return A informação do Serviço.
     */
    @Override
    public String toString() {
        return this.designacao;
    }

    /**
     * Verifica se dois serviços são iguais.
     * 
     * @param outroObjeto Objeto utilizado na comparação.
     * @return Verdadeiro se os dois serviços forem iguais e falso caso não
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
        
        Servico outroServico = (Servico) outroObjeto;
        
        return this.id == outroServico.id;
    }

}
