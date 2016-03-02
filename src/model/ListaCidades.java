package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import static model.BaseDados.oracle;

/**
 * Representa uma lista de cidades.
 */
public class ListaCidades {

    /**
     * Constrói uma instância de lista de cidades.
     */
    public ListaCidades() {
    }

    /**
     * Devolve a lista de cidades presente na base de dados.
     *
     * @return Lista de cidades.
     */
    public List<Cidade> getAllCidades() {
        List<Cidade> listaCidades = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getCidades}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaCidades.add(new Cidade(rs.getInt("id"),
                        rs.getString("nome")));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar as cidades da BD.");
        }

        return listaCidades;
    }
}
