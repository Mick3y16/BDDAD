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
 * Representa uma lista de médicos.
 */
public class ListaMedicos {

    /**
     * Constrói uma instância de lista de médicos.
     */
    public ListaMedicos() {
    }

    /**
     * Devolve a lista de médicos presente na base de dados.
     *
     * @return Lista de médicos.
     */
    public List<Medico> getAllMedicos() {
        List<Medico> listaMedicos = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getMedicos}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaMedicos.add(new Medico(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_consulta"),
                        rs.getDouble("cota_clinica")));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar os médicos da BD.");
        }

        return listaMedicos;
    }

    /**
     * Devolve uma lista de médicos, com cada um dos serviços executados por
     * eles e a lista de materiais utilizados em cada um dentro de um dado
     * periodo.
     *
     * @param dataInicio Inicio do periodo.
     * @param dataFim Fim do periodo.
     * @return Lista de médicos, serviços e materiais.
     */
    public List<Medico> getListaMedicosServicoMateriais(Date dataInicio,
            Date dataFim) {
        List<Medico> listaMedicos = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall(
                    "{? = call getListaMedicosServicoMaterial(?, ?) }");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
            stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                Medico medico = new Medico(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco_consulta"),
                        rs.getDouble("cota_clinica"));
                Servico servico = new Servico(rs.getInt("servico_id"),
                        rs.getString("designacao"),
                        rs.getDouble("valor"));
                Material material = new Material(rs.getInt("material_id"),
                        rs.getString("material_designacao"));

                medico = addMedico(listaMedicos, medico);
                List<Servico> listaServicos = medico.getListaServicos();
                servico = addServico(listaServicos, servico);
                List<Material> listaMateriais = servico.getListaMaterial();
                addMaterial(listaMateriais, material);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException(
                    "Não foi possível carregar os médicos da BD.");
        }

        return listaMedicos;
    }

    /**
     * Adiciona um médico a uma lista de médicos caso ele ainda não exista na
     * mesma.
     * 
     * @param listaMedicos Lista de médicos.
     * @param medico Médico que vai ser adicionado.
     * @return Médico adicionado.
     */
    private Medico addMedico(List<Medico> listaMedicos, Medico medico) {
        for (Medico m : listaMedicos) {
            if (m.equals(medico)) {
                return m;
            }
        }

        listaMedicos.add(medico);
        return medico;
    }

    /**
     * Adiciona um serviço a uma lista de serviços caso ele ainda não exista na
     * mesma.
     * 
     * @param listaServicos Lista de serviços.
     * @param servico Serviço que vai ser adicionado.
     * @return Serviço adicionado.
     */
    private Servico addServico(List<Servico> listaServicos, Servico servico) {
        for (Servico s : listaServicos) {
            if (s.equals(servico)) {
                return s;
            }
        }

        listaServicos.add(servico);
        return servico;
    }

    /**
     * Adiciona um material a uma lista de materiais caso ele ainda não exista
     * na mesma.
     * 
     * @param listaMateriais Lista de materiais.
     * @param material Material que vai ser adicionado.
     */
    private void addMaterial(List<Material> listaMateriais, Material material) {
        for (Material m : listaMateriais) {
            if (m.equals(material)) {
                return;
            }
        }

        listaMateriais.add(material);
    }

}
