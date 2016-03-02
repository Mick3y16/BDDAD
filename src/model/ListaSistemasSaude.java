package model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import static model.BaseDados.oracle;

/**
 * Representa uma lista de sistemas de saúde.
 */
public class ListaSistemasSaude {

    /**
     * Constrói uma instância de lista de sistemas de saúde.
     */
    public ListaSistemasSaude() {
    }

    /**
     * Devolve a lista de sistemas de saúde presente na base de dados.
     *
     * @return Lista de sistema de saúde.
     */
    public List<SistemaSaude> getAllSistemasSaude() {
        List<SistemaSaude> listaSistemasSaude = new ArrayList<>();

        try {
            CallableStatement stmt = oracle.prepareCall("{? = call getSistemasSaude}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR, "SYS_REFCURSOR");
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                listaSistemasSaude.add(
                        rs.getInt("id"), new SistemaSaude(rs.getInt("id"),
                        rs.getString("designacao")));
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(
                    "Não foi possível carregar os sistemas de saúde da BD.");
        }

        return listaSistemasSaude;
    }
}
