package model;

import java.sql.SQLException;

/**
 * Representa uma clinica.
 */
public class Clinica {

    /**
     * Constrói uma instância de clinica.
     * 
     * @throws java.sql.SQLException Lançada quando a ligação à BD falha.
     */
    public Clinica() throws SQLException {
        // Singleton
        new BaseDados();
    }

    /**
     * Devolve uma lista de cidades;
     *
     * @return Lista de cidades;
     */
    public ListaCidades getListaCidades() {
        return new ListaCidades();
    }

    /**
     * Devolve uma lista de paises;
     *
     * @return Lista de paises;
     */
    public ListaPaises getListaPaises() {
        return new ListaPaises();
    }

    /**
     * Devolve uma lista de sistemas de saúde.
     *
     * @return Lista de sistemas de saúde.
     */
    public ListaSistemasSaude getListaSistemasSaude() {
        return new ListaSistemasSaude();
    }

    /**
     * Devolve uma lista de médicos.
     *
     * @return Lista de médicos.
     */
    public ListaMedicos getListaMedicos() {
        return new ListaMedicos();
    }

    /**
     * Devolve uma lista de pacientes.
     *
     * @return Lista de pacientes.
     */
    public ListaPacientes getListaPacientes() {
        return new ListaPacientes();
    }

    /**
     * Devolve uma lista de consultas.
     *
     * @return Lista de consultas.
     */
    public ListaConsultas getListaConsultas() {
        return new ListaConsultas();
    }

    /**
     * Devolve uma lista de serviços.
     *
     * @return Lista de serviços.
     */
    public ListaServicos getListaServicos() {
        return new ListaServicos();
    }

    /**
     * Devolve uma lista de tratamentos.
     *
     * @return Lista de tratamentos.
     */
    public ListaTratamentos getListaTratamentos() {
        return new ListaTratamentos();
    }

    /**
     * Devolve uma lista de materiais.
     * 
     * @return Lista de materiais.
     */
    public ListaMateriais getListaMateriais(){
        return new ListaMateriais();
    }
}
