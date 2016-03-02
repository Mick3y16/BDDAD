package controller;

import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Clinica;
import model.Material;
import model.Servico;

/**
 * Representa um controlador que gere a listagem os serviços realizados e o
 * material utilizado em cada um, por médico.
 */
public class CriarListagemController {

    /**
     * Clinica da qual vai ser apresentada a listagem dos serviços realizados e
     * o material utilizado em cada um, por médico.
     */
    private Clinica clinica;

    /**
     * Constrói uma instância de um controlador que gere a listagem dos serviços
     * realizados e o material utilizado em cada um, por médico, recebendo uma
     * clinica por parametro.
     *
     * @param clinica Clinica da qual vai ser apresentada a listagem dos
     * serviços realizados e o material utilizado em cada um, por médico.
     */
    public CriarListagemController(Clinica clinica) {
        this.clinica = clinica;
    }

    /**
     * Devolve uma lista de médicos, com cada um dos serviços executados por
     * eles e a lista de materiais utilizados em cada um dentro de um dado
     * periodo.
     *
     * @param dataInicio Inicio do periodo.
     * @param dataFim Final do periodo.
     * @return Modelo de uma lista de médicos, serviços e materiais.
     */
    public DefaultComboBoxModel getListaMedicosServicosMaterial(Date dataInicio,
            Date dataFim) {
        return new DefaultComboBoxModel<>(
                this.clinica.getListaMedicos().getListaMedicosServicoMateriais(
                        dataInicio, dataFim).toArray());
    }

}
