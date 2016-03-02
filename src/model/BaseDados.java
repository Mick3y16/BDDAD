package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Representa uma instância de Base de Dados responsável por establecer ligações
 * à base de dados de forma a permitir a execução de procedimentos.
 */
final class BaseDados {

    /**
     * Ligação à base de dados, assim que establecida.
     */
    public static Connection oracle;

    /**
     * Representa uma instância de uma base de dados.
     */
    public BaseDados() throws SQLException {
        String driver = "oracle:thin";
        String endereco = "gandalf.dei.isep.ipp.pt";
        String porta = "1521";
        String utilizador = "BDDAD15_DC8";
        String palavraPasse = "holdinghands";
        oracle = DriverManager.getConnection(
                "jdbc:" + driver + ":@" + endereco + ":" + porta + "/pdborcl",
                utilizador,
                palavraPasse);
    }

}
