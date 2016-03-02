package controller;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.Clinica;
import model.Servico;
import model.Tratamento;

/**
 * Representa um controlador que permite registar os vários serviços realizados
 * numa consulta.
 */
public class RegistarServicosConsultaController {

    /**
     * Clinica dentária onde se vai registar os serviços na consulta.
     */
    private Clinica clinica;

    /**
     * Constrói uma instância de um controlador que irá registar serviços na
     * consulta.
     *
     * @param clinica Clinica dentária onde se vai registar os serviços na
     * consulta.
     */
    public RegistarServicosConsultaController(Clinica clinica) {
        this.clinica = clinica;
    }

    /**
     * Devolve um modelo com uma lista de pacientes.
     *
     * @return Modelo com uma lista de pacientes.
     */
    public DefaultComboBoxModel getListaPacientes() {
        return new DefaultComboBoxModel<>(
                this.clinica.getListaPacientes().getAllPacientes().toArray());
    }

    /**
     * Devolve um modelo com uma lista de consultas para um dado paciente.
     *
     * @param paciente Id do paciente do qual se pretende obter as consultas. 
     * @return Modelo com uma lista de consultas.
     */
    public DefaultComboBoxModel getListaConsultasPaciente(int paciente) {
        return new DefaultComboBoxModel<>(
                this.clinica.getListaConsultas().getAllConsultasPaciente(
                        paciente).toArray());
    }

    /**
     * Devolve um vetor que contém o nome de cada serviço.
     * 
     * @return Vetor com o nome de cada serviço.
     */
    public String[] getNomeServicos() {
        List<Servico> listaServicos
                = this.clinica.getListaServicos().getAllServicos();
        String[] nomeServicos = new String[listaServicos.size()];
        
        int i = 0;
        for (Servico servico : listaServicos) {
            nomeServicos[i] = servico.toString();
            i++;
        }
        
        return nomeServicos;
    }

    /**
     * Devolve um modelo com uma lista de tratamentos por realizar de um dado
     * paciente.
     *
     * @param paciente ID do paciente.
     * @return Modelo com uma lista de tratamentos por realizar.
     */
    public DefaultListModel<Tratamento> getListaTratamentoPaciente(int paciente) {
        DefaultListModel<Tratamento> listaTratamentos = new DefaultListModel<>();
        
        for (Tratamento tratamento : this.clinica.getListaTratamentos().
                getTratamentosPacientePorRealizar(paciente)) {
            listaTratamentos.addElement(tratamento);
        }
        
        return listaTratamentos;
    }

    /**
     * Devolve um modelo com uma lista de tratamentos realizados numa consulta
     * por um dado paciente.
     * 
     * @param paciente Paciente que realizou os tratamentos.
     * @param consulta Consulta em que os tratamentos foram realizados.
     * @return Modelo com uma lista de tratamentos realizados.
     */
    public DefaultListModel<Tratamento> getListaTratamentoPacienteConsulta(
            int paciente, int consulta) {
        DefaultListModel<Tratamento> listaTratamentos = new DefaultListModel<>();
        
        for (Tratamento tratamento : this.clinica.getListaTratamentos().
                getTratamentosPacienteConsulta(paciente, consulta)) {
            listaTratamentos.addElement(tratamento);
        }
        
        return listaTratamentos;
    }

    /**
     * Regista um tratamento realizado por um paciente numa dada consulta.
     * 
     * @param tratamento Tratamento que se pretende registar
     * @param consulta Consulta na qual o tratamento vai ser registado.
     * @return Verdadeiro se o registo for realizado com sucesso, caso não seja
     * lança uma exceção.
     */
    public boolean registarTratamentoConsulta(int tratamento, int consulta) {
        return this.clinica.getListaTratamentos().registarTratatamentoConsulta(
                tratamento, consulta);
    }

}
