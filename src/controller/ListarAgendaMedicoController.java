package controller;

import javax.swing.DefaultListModel;
import model.Clinica;
import model.Consulta;
import model.Medico;

/**
 * Representa uma instância de um controlador que irá listar a agenda de um
 * médico num dado mês.
 */
public class ListarAgendaMedicoController {

    /**
     * Clinica dentária da qual se vai extrair a agenda do médico no dado mês.
     */
    private Clinica clinica;

    /**
     * Constrói uma instância de um controlador que irá listar a agenda de um
     * médico num dado mês.
     *
     * @param clinica Clinica dentária da qual se vai extrair a agenda do médico
     * no dado mês.
     */
    public ListarAgendaMedicoController(Clinica clinica) {
        this.clinica = clinica;
    }

    /**
     * Devolve um modelo com uma lista de médicos.
     *
     * @return Modelo com uma lista de médicos.
     */
    public DefaultListModel<Medico> getListaMedicos() {
        DefaultListModel<Medico> listaMedicos = new DefaultListModel();

        for (Medico medico : this.clinica.getListaMedicos().getAllMedicos()) {
            listaMedicos.addElement(medico);
        }

        return listaMedicos;
    }

    /**
     * Devolve um modelo com a lista de consultas de um médico num dado mês.
     *
     * @param medico Médico do qual se pretende a lista de consultas.
     * @param mes Mês do qual se pretende a lista de consultas.
     * @return Modelo com a lista de consultas de um médico num dado mês.
     */
    public DefaultListModel<Consulta> getListaConsultasMedicoMes(int medico, int mes) {
        DefaultListModel<Consulta> listaConsulta = new DefaultListModel();

        for (Consulta consulta : this.clinica.getListaConsultas().
                getAllConsultasMedicoMes(medico, mes)) {
            listaConsulta.addElement(consulta);
        }

        return listaConsulta;
    }

}
