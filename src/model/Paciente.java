package model;

import java.util.Date;
import java.util.List;

/**
 * Representa um paciente através de um id, de um bi, um nif, um nome completo,
 * um endereço, uma cidade, um pais, um telefone, uma data, um médico e um
 * sistema de saúde.
 */
public class Paciente {

    /**
     * ID do paciente.
     */
    private int id;
    
    /**
     * BI do paciente.
     */
    private String bi;

    /**
     * NIF do paciente.
     */
    private int nif;

    /**
     * Nome da paciente.
     */
    private String nomeCompleto;

    /**
     * Endereço do paciente.
     */
    private String endereco;

    /**
     * Cidade do paciente.
     */
    private int cidade;

    /**
     * Pais do paciente.
     */
    private int pais;

    /**
     * Telefone do paciente.
     */
    private String telefone;

    /**
     * Data de nascimento do paciente.
     */
    private Date dataNascimento;

    /**
     * Médico do paciente.
     */
    private int medico;

    /**
     * Lista de sistemas de saúde do paciente.
     */
    private List<Integer> sistemasSaude;
    
    /**
     * Constrói uma instância de cidade recebendo um nome por parametro.
     *
     * @param bi BI do paciente.
     * @param nif NIF do paciente.
     * @param nomeCompleto Nome completo do paciente.
     * @param endereco Endereço do paciente.
     * @param cidade Cidade do paciente.
     * @param pais Pais do paciente.
     * @param telefone Telefone do paciente.
     * @param dataNascimento Data de nascimento.
     * @param medico Médico do paciente.
     * @param sistemasSaude Sistemas de saude do paciente.
     */
    public Paciente(String bi, int nif, String nomeCompleto, String endereco,
            int cidade, int pais, String telefone, Date dataNascimento, 
            int medico, List<Integer> sistemasSaude) {
        this.setBi(bi);
        this.setNif(nif);
        this.setNomeCompleto(nomeCompleto);
        this.setEndereco(endereco);
        this.setCidade(cidade);
        this.setPais(pais);
        this.setTelefone(telefone);
        this.setDataNascimento(dataNascimento);
        this.setMedico(medico);
        this.setSistemasSaude(sistemasSaude);
    }

    /**
     * Constrói uma instância de cidade recebendo um nome por parametro.
     *
     * @param id ID do paciente.
     * @param bi BI do paciente.
     * @param nif NIF do paciente.
     * @param nomeCompleto Nome completo do paciente.
     * @param endereco Endereço do paciente.
     * @param cidade Cidade do paciente.
     * @param pais Pais do paciente.
     * @param telefone Telefone do paciente.
     * @param dataNascimento Data de nascimento.
     * @param medico Médico do paciente.
     * @param sistemasSaude Sistemas de saude do paciente.
     */
    public Paciente(int id, String bi, int nif, String nomeCompleto,
            String endereco, int cidade, int pais, String telefone, 
            Date dataNascimento, int medico,
            List<Integer> sistemasSaude) {
        this(bi, nif, nomeCompleto, endereco, cidade, pais, telefone, 
                dataNascimento, medico, sistemasSaude);
        this.setId(id);
    }

    /**
     * Devolve o ID do paciente.
     * 
     * @return ID do paciente.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Devolve o BI do paciente.
     *
     * @return BI do paciente.
     */
    public String getBi() {
        return this.bi;
    }

    /**
     * Devolve o NIF do paciente.
     *
     * @return NIF do paciente.
     */
    public int getNif() {
        return this.nif;
    }

    /**
     * Devolve o nome completo do paciente.
     *
     * @return Nome completo do paciente.
     */
    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    /**
     * Devolve o endereço do paciente.
     *
     * @return Endereço do paciente.
     */
    public String getEndereco() {
        return this.endereco;
    }

    /**
     * Devolve a cidade do paciente.
     *
     * @return Cidade do paciente.
     */
    public int getCidade() {
        return this.cidade;
    }

    /**
     * Devolve o pais do paciente.
     *
     * @return Pais do paciente.
     */
    public int getPais() {
        return this.pais;
    }

    /**
     * Devolve o telefone do paciente.
     *
     * @return Telefone do paciente.
     */
    public String getTelefone() {
        return this.telefone;
    }

    /**
     * Devolve a data de nascimento do paciente.
     *
     * @return Data de nascimento do paciente.
     */
    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    /**
     * Devolve o médico por omissão do paciente.
     *
     * @return Médico por omissão do paciente.
     */
    public int getMedico() {
        return medico;
    }

    /**
     * Devolve os sistemas de saúde do paciente.
     *
     * @return Sistemas de saúde do paciente.
     */
    public List<Integer> getSistemasSaude() {
        return sistemasSaude;
    }

    /**
     * Modifica o ID do paciente.
     * 
     * @param id ID do paciente.
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Modifica o BI do paciente.
     *
     * @param bi Novo BI do paciente.
     */
    public final void setBi(String bi) {
        if (bi == null || bi.trim().isEmpty()) {
            throw new IllegalArgumentException("Não preencheu o campo do BI.");
        }

        if (!bi.matches("[0-9]{9}[A-Z]{2}[0-9]")) {
            throw new IllegalArgumentException(
                    "O BI deve obdecer ao formato: 123456789AB1.");
        }

        this.bi = bi;
    }

    /**
     * Modifica o NIF do paciente.
     *
     * @param nif Novo NIF do paciente.
     */
    public final void setNif(int nif) {
        if (99999999 > nif && nif < 1000000000) {
            throw new IllegalArgumentException(
                    "O NIF deve ter possuir 9 digitos.");
        }

        this.nif = nif;
    }

    /**
     * Modifica o nome completo do paciente.
     *
     * @param nomeCompleto Novo nome completo do paciente.
     */
    public final void setNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Não preencheu o campo do nome.");
        }

        this.nomeCompleto = nomeCompleto;
    }

    /**
     * Modifica o endereço do paciente.
     *
     * @param endereco Novo endereço do paciente.
     */
    public final void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Não preencheu o campo do endereço.");
        }

        this.endereco = endereco;
    }

    /**
     * Modifica a cidade do paciente.
     *
     * @param cidade Nova cidade do paciente.
     */
    public final void setCidade(int cidade) {
        if (cidade < 0) {
            throw new IllegalArgumentException(
                    "Não selecionou uma cidade válida.");
        }

        this.cidade = cidade;
    }

    /**
     * Modifica o pais do paciente.
     *
     * @param pais Novo pais do paciente.
     */
    public final void setPais(int pais) {
        if (pais < 0) {
            throw new IllegalArgumentException(
                    "Não selecionou um pais válido.");
        }

        this.pais = pais;
    }

    /**
     * Modifica o telefone do paciente.
     *
     * @param telefone Novo telefone do paciente.
     */
    public final void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Não preencheu o campo do telefone.");
        }

        if (!telefone.matches("[0-9]{2}-[0-9]{7}")) {
            throw new IllegalArgumentException(
                    "O telefone deve obdecer ao formato: 91-1234567.");
        }

        this.telefone = telefone;
    }

    /**
     * Modifica a data de nascimento do paciente.
     *
     * @param dataNascimento Nova data de nascimento do paciente.
     */
    public final void setDataNascimento(Date dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException(
                    "Não preencheu o campo da data de nascimento.");
        }

        this.dataNascimento = dataNascimento;
    }

    /**
     * Modifica o médico do paciente.
     *
     * @param medico Novo médico do paciente.
     */
    public final void setMedico(int medico) {
        if (medico < 0) {
            throw new IllegalArgumentException(
                    "Não selecionou um médico válido.");
        }

        this.medico = medico;
    }

    /**
     * Modifica os sistemas de saúde do paciente.
     *
     * @param sistemasSaude Novos sistemas de saúde do paciente.
     */
    public final void setSistemasSaude(List<Integer> sistemasSaude) {
        this.sistemasSaude = sistemasSaude;
    }

    /**
     * Valida o paciente na sua totalidade lançando exceções caso algo falhe.
     *
     * @return Verdadeiro se o paciente for válido.
     */
    public boolean validar() {
        if (this.bi == null || this.bi.trim().isEmpty()) {
            throw new IllegalArgumentException("Não preencheu o campo do BI.");
        }

        if (!this.bi.matches("[0-9]{9}[A-Z]{2}[0-9]")) {
            throw new IllegalArgumentException(
                    "O BI deve obdecer ao formato: 123456789AB1.");
        }

        if (99999999 > this.nif && this.nif < 1000000000) {
            throw new IllegalArgumentException(
                    "O NIF deve ter possuir 9 digitos.");
        }

        if (this.nomeCompleto == null || this.nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Não preencheu o campo do nome.");
        }

        if (this.endereco == null || this.endereco.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Não preencheu o campo do endereço.");
        }

        if (this.cidade < 0) {
            throw new IllegalArgumentException(
                    "Não selecionou uma cidade válida.");
        }

        if (this.pais < 0) {
            throw new IllegalArgumentException(
                    "Não selecionou um pais válido.");
        }

        if (this.telefone == null || this.telefone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Não preencheu o campo do telefone.");
        }

        if (!this.telefone.matches("[0-9]{2}-[0-9]{7}")) {
            throw new IllegalArgumentException(
                    "O telefone deve obdecer ao formato: 91-1234567.");
        }

        if (this.medico < 0) {
            throw new IllegalArgumentException(
                    "Não selecionou um médico válido.");
        }

        return true;
    }

    /**
     * Imprime a informação de uma cidade.
     *
     * @return informação de uma cidade.
     */
    @Override
    public String toString() {
        return this.getNomeCompleto();
    }

}
