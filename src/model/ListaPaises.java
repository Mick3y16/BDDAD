package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static model.BaseDados.oracle;
import oracle.jdbc.OracleTypes;

/**
 * Representa uma lista de paises.
 */
public class ListaPaises {

    /**
     * Constrói uma instância de lista de paises.
     */
    public ListaPaises() {
    }

    /**
     * Devolve a lista de paises presente na base de dados.
     *
     * @return Lista de paises.
     */
    public List<Pais> getAllPaises() {
        List<Pais> listaPaises = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getPaises}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaPaises.add(new Pais(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar os paises da BD.");
        }

        return listaPaises;
    }
}
