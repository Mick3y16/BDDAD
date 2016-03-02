package controller;

import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.Clinica;
import model.SistemaSaude;

/**
 * Representa uma instância de um controlador que irá registar um paciente na
 * base de dados.
 */
public class RegistarPacienteController {

    /**
     * Clinica dentária onde se vai registar o paciente.
     */
    private Clinica clinica;

    /**
     * Constrói uma instância de um controlador que irá registar uma paciente na
     * base de dados recebendo a clinica por parametro.
     *
     * @param clinica Clinica dentária onde se vai registar o paciente.
     */
    public RegistarPacienteController(Clinica clinica) {
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
     * Regista o paciente na base de dados após o preenchimento do formulário.
     *
     * @param BI BI do paciente.
     * @param NIF NIF do paciente.
     * @param nomeCompleto Nome completo do paciente.
     * @param endereco Endereço do paciente.
     * @param cidade Cidade do paciente.
     * @param pais Pais do paciente.
     * @param telefone Telefone do paciente.
     * @param data Data de nascimento do paciente.
     * @param medico Médico por omissão do paciente.
     * @param sisSaude Lista de sistemas de saúde do paciente.
     * @return Verdadeiro se o paciente for registado com sucesso, caso
     * contrário é lançada uma exceção com o erro.
     */
    public boolean registarPaciente(String BI, int NIF, String nomeCompleto,
            String endereco, int cidade, int pais, String telefone,
            Date data, int medico, int sisSaude[]) {
        return this.clinica.getListaPacientes().registarPaciente(BI, NIF,
                nomeCompleto, endereco, cidade, pais, telefone, data, medico,
                sisSaude);
    }

}
