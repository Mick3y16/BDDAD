package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.BaseDados.oracle;
import oracle.jdbc.OracleTypes;

/**
 * Representa uma lista de materiais.
 */
public class ListaMateriais {

    /**
     * Constrói uma instância de lista de materiais.
     */
    public ListaMateriais() {
    }
    
     /**
     * Devolve a lista de materiais presente na base de dados.
     *
     * @return Lista de materiais.
     */
    public List<Material> getAllMateriais() {
        List<Material> listaMateriais = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getMateriais}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaMateriais.add(new Material(rs.getInt("id"),
                        rs.getString("designacao")));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar os materiais da BD.");
        }

        return listaMateriais;
    }
    
}
