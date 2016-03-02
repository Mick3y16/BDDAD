package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.BaseDados.oracle;
import oracle.jdbc.OracleTypes;

/**
 * Representa uma lista de Serviços.
 */
public class ListaServicos {

    /**
     * Constrói uma instância de lista de Serviços.
     */
    public ListaServicos() {
    }

    /**
     * Devolve a lista de serviços presente na base de dados.
     *
     * @return Lista de serviços.
     */
    public List<Servico> getAllServicos() {
        List<Servico> listaServicos = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getServicos}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaServicos.add(new Servico(rs.getInt("id"),
                        rs.getString("designacao"), rs.getDouble("valor")));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar os serviços da BD.");
        }

        return listaServicos;
    }
    
}
