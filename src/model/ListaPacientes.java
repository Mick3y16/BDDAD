package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static model.BaseDados.oracle;
import oracle.jdbc.OracleTypes;

/**
 * Representa uma lista de pacientes.
 */
public class ListaPacientes {

    /**
     * Constrói uma instância de lista de pacientes.
     */
    public ListaPacientes() {
    }

    /**
     * Regista um novo paciente na base de dados.
     *
     * @param BI BI do paciente.
     * @param NIF NIF do paciente.
     * @param nomeCompleto Nome completo do paciente.
     * @param endereco Endereço do paciente.
     * @param cidade Cidade do paciente.
     * @param pais Pais do paciente.
     * @param telefone Telefone do paciente.
     * @param data Data de nascimento do paciente.
     * @param medico Médico do paciente.
     * @param sisSaude Sistemas de saúde do paciente.
     * @return Verdadeiro se o paciente for registado com sucesso, caso não seja
     * possível o registo, é lançada uma exceção.
     */
    public boolean registarPaciente(String BI, int NIF, String nomeCompleto,
            String endereco, int cidade, int pais, String telefone,
            Date data, int medico, int sisSaude[]) {
        boolean resultado = false;

        Paciente paciente = new Paciente(BI, NIF, nomeCompleto, endereco, cidade,
                pais, telefone, data, medico, new ArrayList<Integer>());

        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{ call registarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, paciente.getBi());
            stmt.setInt(2, paciente.getNif());
            stmt.setString(3, paciente.getNomeCompleto());
            stmt.setString(4, paciente.getEndereco());
            stmt.setInt(5, paciente.getCidade());
            stmt.setInt(6, paciente.getPais());
            stmt.setString(7, paciente.getTelefone());
            stmt.setDate(8, new java.sql.Date(paciente.getDataNascimento().getTime()));
            stmt.setInt(9, paciente.getMedico());
            stmt.execute();

            return true;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Atualiza os dados de um paciente na base de dados.
     *
     * @param paciente Paciente cujos os dados vão ser atualizados.
     * @return Verdadeiro se os dados forem atualizados com sucesso, caso não
     * seja possível atualizar os dados, é lançada uma exceção.
     */
    public boolean atualizarPaciente(Paciente paciente) {
        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{ call atualizarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setInt(1, paciente.getId());
            stmt.setString(2, paciente.getBi());
            stmt.setInt(3, paciente.getNif());
            stmt.setString(4, paciente.getNomeCompleto());
            stmt.setString(5, paciente.getEndereco());
            stmt.setInt(6, paciente.getCidade());
            stmt.setInt(7, paciente.getPais());
            stmt.setString(8, paciente.getTelefone());
            stmt.setDate(9, new java.sql.Date(paciente.getDataNascimento().getTime()));
            stmt.setInt(10, paciente.getMedico());
            stmt.execute();

            return true;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Devolve a lista de pacientes presente na base de dados.
     *
     * @return Lista de pacientes.
     */
    public List<Paciente> getAllPacientes() {
        List<Paciente> listaPacientes = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getPacientes}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaPacientes.add(new Paciente(rs.getInt("id"),
                        rs.getString("bi"), rs.getInt("nc"),
                        rs.getString("nome_completo"),
                        rs.getString("endereco"), rs.getInt("cidade"),
                        rs.getInt("pais"), rs.getString("telefone"),
                        rs.getDate("data_nascimento"),
                        rs.getInt("medico_por_omissao"), new ArrayList<Integer>()));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar os pacientes da BD.");
        }

        return listaPacientes;
    }

}
