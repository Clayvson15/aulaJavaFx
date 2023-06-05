package br.com.senac.aula.cadastroClientes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDataBase {
    private Connection conn = null;

    public synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cadastro de Clientes", "postgres", "bolado123");
        }

        return conn;
    }
}