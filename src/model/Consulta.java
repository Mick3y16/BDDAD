package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa uma consulta através de um id, um paciente, um médico, uma data,
 * uma hora de início, uma hora de fim e estado.
 */
public class Consulta {

    /**
     * Id da consulta.
     */
    private int id;
    
    /**
     * Paciente da consulta.
     */
    private int paciente;
    
    /**
     * Médico da consulta.
     */
    private int medico;
    
    /**
     * Data da consulta.
     */
    private Date data;
    
    /**
     * Hora de inicio da consulta.
     */
    private double horaInicio;
    
    /**
     * Hora de término da consulta.
     */
    private double horaFim;
    
    /**
     * Estado da consulta.
     */
    private int estado;

    /**
     * Constrói uma instância de consulta recebendo por parametro um id, um
     * paciente, um médico, uma data, uma hora de inicio, uma hora de fim e
     * um estado.
     * 
     * @param id Id da consulta.
     * @param paciente Paciente da consulta.
     * @param medico Médico da consulta.
     * @param data Data da consulta.
     * @param horaInicio Hora de ínicio da consulta.
     * @param horaFim Hora de término da consulta.
     * @param estado 
     */
    public Consulta(int id, int paciente, int medico, Date data,
            double horaInicio, double horaFim, int estado) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.estado = estado;
    }

    /**
     * Retorna o ID da consulta.
     * 
     * @return ID da consulta.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Imprime a informação de uma consulta.
     * 
     * @return Informação de uma consulta.
     */
    @Override
    public String toString() {
        return String.format("%s - das %.2fh às %.2fh",
                new SimpleDateFormat("dd/MM/yy").format(this.data),
                this.horaInicio, this.horaFim);
    }
    
    
}
