package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.BaseDados.oracle;
import oracle.jdbc.OracleTypes;

/**
 * Representa uma lista de consultas.
 */
public class ListaConsultas {

    /**
     * Constrói uma instância de lista de consultas.
     */
    public ListaConsultas() {
    }

    /**
     * Devolve uma lista de consultas presente na base de dados, relativas a um
     * dado médico num dado mês.
     *
     * @param medico Médico do qual se pretendem saber as consultas.
     * @param mes Mês do qual se pretendem saber as consultas.
     * @return
     */
    public List<Consulta> getAllConsultasMedicoMes(int medico, int mes) {
        List<Consulta> listaConsulta = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{? = call getAgendaConsultasMedicoMes(?, ?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.setInt(2, medico);
            stmt.setInt(3, mes);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaConsulta.add(new Consulta(rs.getInt("id"),
                        rs.getInt("paciente"), rs.getInt("medico"),
                        rs.getDate("data"), rs.getDouble("hora_inicio"),
                        rs.getDouble("hora_fim"), rs.getInt("estado")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException(
                    "Não foi possível carregar as consultas da BD.");
        }

        return listaConsulta;
    }
    
    /**
     * Devolve uma lista de consultas presente na base de dados relativas a um
     * dado paciente.
     * 
     * @param paciente Paciente do qual se pretende saber as consultas.
     * 
     * @return Lista de consultas de uma dado paciente.
     */
    public List<Consulta> getAllConsultasPaciente(int paciente){
        List<Consulta> listaConsulta = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{? = call getConsultasPaciente(?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.setInt(2, paciente);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaConsulta.add(new Consulta(rs.getInt("id"),
                        rs.getInt("paciente"), rs.getInt("medico"),
                        rs.getDate("data"), rs.getDouble("hora_inicio"),
                        rs.getDouble("hora_fim"), rs.getInt("estado")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException(
                    "Não foi possível carregar as consultas da BD.");
        }

        return listaConsulta;
    }

}
