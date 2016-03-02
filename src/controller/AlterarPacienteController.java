package controller;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.Clinica;
import model.Paciente;
import model.SistemaSaude;

/**
 * Representa uma instância de um controlador que permite alterar os dados de 
 * um paciente.
 */
public class AlterarPacienteController {

    /**
     * Clinica dentária onde se vai alterar o paciente.
     */
    private Clinica clinica;

    /**
     * Constrói uma instância de um controlador que permite alterar os dados de
     * um paciente.
     * 
     * @param clinica Clinica na qual vão ser alterados os dados de um paciente.
     */
    public AlterarPacienteController(Clinica clinica) {
        this.clinica = clinica;
    }
    
    /**
     * Devolve um modelo com uma lista de cidades.
     *
     * @return Modelo com uma lista de cidades.
     */
    public DefaultComboBoxModel getListaCidades() {
        return new DefaultComboBoxModel<>(
                this.clinica.getListaCidades().getAllCidades().toArray());
    }

    /**
     * Devolve um modelo com uma lista de paises.
     *
     * @return Modelo com uma lista de paises.
     */
    public DefaultComboBoxModel getListaPaises() {
        return new DefaultComboBoxModel<>(
                this.clinica.getListaPaises().getAllPaises().toArray());
    }

    /**
     * Devolve um modelo com uma lista de sistemas de saúde.
     *
     * @return Modelo com uma lista de sistemas de saúde.
     */
    public DefaultListModel getListaSistemasSaude() {
        DefaultListModel<SistemaSaude> listaModelo = new DefaultListModel();

        for (SistemaSaude sistemaSaude
                : this.clinica.getListaSistemasSaude().getAllSistemasSaude()) {
            listaModelo.addElement(sistemaSaude);
        }

        return listaModelo;
    }

    /**
     * Devolve um modelo com uma lista de médicos.
     *
     * @return Modelo com uma lista de médicos.
     */
    public DefaultComboBoxModel getListaMedicos() {
        return new DefaultComboBoxModel<>(
                this.clinica.getListaMedicos().getAllMedicos().toArray());
    }
    
    /**
     * Devolve um modelo com uma lista de pacientes.
     * 
     * @return Modelo com uma lista de pacientes.
     */
    public DefaultComboBoxModel getListaPacientes(){
        return new DefaultComboBoxModel<>(this.clinica.getListaPacientes().
                getAllPacientes().toArray());
    }
    
    /**
     * Permite a alterações dos dados de um paciente. 
     * 
     * @param paciente Paciente a alterar os dados.
     * @return Verdadeiro se os dados foram alterados, falso se não foram.
     */
    public boolean alterarPaciente(Paciente paciente){
        return this.clinica.getListaPacientes().atualizarPaciente(paciente);
    }
    
}
