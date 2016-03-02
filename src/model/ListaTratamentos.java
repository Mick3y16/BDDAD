package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.BaseDados.oracle;
import oracle.jdbc.OracleTypes;

/**
 * Representa uma lista de tratamentos.
 */
public class ListaTratamentos {

    /**
     * Constrói uma instância de lista de tratamentos
     */
    public ListaTratamentos() {
    }

    /**
     * Regista um tratamento realizado por um paciente numa dada consulta.
     *
     * @param tratamento Tratamento que se pretende registar.
     * @param consulta Consulta na qual o tratamento vai ser registado.
     * @return Verdadeiro se o registo for realizado com sucesso, caso não seja
     * lança uma exceção.
     */
    public boolean registarTratatamentoConsulta(int tratamento, int consulta) {
        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{ call registarTratamento(?, ?) }");
            stmt.setInt(1, tratamento);
            stmt.setInt(2, consulta);
            stmt.execute();
            
            return true;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Devolve uma lista de tratamentos presentes na base de dados, ainda por
     * realizar, relativos a um dado paciente.
     *
     * @param paciente Paciente do qual se pretendem os tratamentos ainda por
     * realizar.
     * @return Lista de tratamentos por realizar.
     */
    public List<Tratamento> getTratamentosPacientePorRealizar(int paciente) {
        List<Tratamento> listaTratamentos = new ArrayList();

        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{? = call getTratamentosPacientePorR(?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.setInt(2, paciente);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaTratamentos.add(new Tratamento(rs.getInt("id"),
                        rs.getInt("consulta"), rs.getInt("orcamento"),
                        rs.getInt("servico")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException(
                    "Não foi possível carregar os tratamentos da BD.");
        }

        return listaTratamentos;
    }

    /**
     * Devolve uma lista de tratamentos presentes na base de dados, realizados
     * por um paciente, numa dada consulta.
     *
     * @param paciente Paciente do qual se pretendem saber os tratamentos
     * realizados.
     * @param consulta Consulta na qual os tratamentos foram realizados.
     * @return Lista de tratamentos realizados na consulta.
     */
    public List<Tratamento> getTratamentosPacienteConsulta(int paciente,
            int consulta) {
        List<Tratamento> listaTratamentos = new ArrayList();

        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{? = call getTratamentosPacienteConsulta(?, ?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.setInt(2, paciente);
            stmt.setInt(3, consulta);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaTratamentos.add(new Tratamento(rs.getInt("id"),
                        rs.getInt("consulta"), rs.getInt("orcamento"),
                        rs.getInt("servico")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException(
                    "Não foi possível carregar os tratamentos da BD.");
        }

        return listaTratamentos;
    }

}
