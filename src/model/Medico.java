package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um médico através de um id, um nome, um preço por consulta e uma
 * cota paga à clínica.
 */
public class Medico {

    /**
     * Id do médico.
     */
    private int id;

    /**
     * Nome do médico.
     */
    private String nome;

    /**
     * Preço por consulta do médico.
     */
    private double precoConsulta;

    /**
     * Cota paga à clínica pelo médico.
     */
    private double cotaClinica;

    /**
     * Lista de serviços desempenhados pelo médico.
     */
    private List<Servico> listaServicos;

    /**
     * Constrói uma instância de médico recebendo um id, um nome, um preço por
     * consulta e uma cota paga à clínica por parametro.
     *
     * @param id Id do médico na base de dados.
     * @param nome Nome do médico.
     * @param precoConsulta Preço por consulta do médico.
     * @param cotaClinica Cota paga à clinica pelo médico.
     */
    public Medico(int id, String nome, double precoConsulta, double cotaClinica) {
        this.id = id;
        this.nome = nome;
        this.precoConsulta = precoConsulta;
        this.cotaClinica = cotaClinica;
        this.listaServicos = new ArrayList<>();
    }

    /**
     * Devolve o id do médico.
     *
     * @return Id do médico.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Devolve a lista de serviços realizados pelo médico.
     * 
     * @return Lista de serviços realizados pelo médico.
     */
    public List<Servico> getListaServicos() {
        return this.listaServicos;
    }

    /**
     * Imprime a informação de um médico.
     *
     * @return informação de um médico.
     */
    @Override
    public String toString() {
        return this.nome;
    }

    /**
     * Verifica se dois médicos são iguais.
     * 
     * @param outroObjeto Objeto utilizado na comparação.
     * @return Verdadeiro se os dois médicos forem iguais e falso caso não
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
        
        Medico outroMedico = (Medico) outroObjeto;
        
        return this.id == outroMedico.id;
    }

}
