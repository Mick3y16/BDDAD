package model;

/**
 * Representa um tratamento através de um id, uma consulta, um orçamento e um
 * serviço.
 */
public class Tratamento {

    /**
     * Id do tratamento.
     */
    private int id;

    /**
     * Consulta na qual o tratamento ocorreu.
     */
    private Integer consulta;

    /**
     * Orçamento ao qual o tratamento pretence.
     */
    private int orcamento;

    /**
     * Serviço ao qual o tratamento se refere.
     */
    private int servico;

    /**
     * Constrói uma instância de tratamento recebendo por parametro um id, uma
     * consulta, um orçamento e um serviço.
     *
     * @param id Id do tratamento.
     * @param consulta Consulta na qual o tratamento ocorreu.
     * @param orcamento Orçamento ao qual o tratamento pretence.
     * @param servico Serviço ao qual o tratamento se refere.
     */
    public Tratamento(int id, Integer consulta, int orcamento, int servico) {
        this.id = id;
        this.consulta = consulta;
        this.orcamento = orcamento;
        this.servico = servico;
    }

    /**
     * Retorna o ID do tratamento.
     *
     * @return ID do tratamento.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Imprime a informação de um tratamento.
     *
     * @return Informação de um tratamento.
     */
    @Override
    public String toString() {
        return String.valueOf(this.servico);
    }

}
