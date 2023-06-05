package br.com.senac.aula.cadastroClientes.services;

import br.com.senac.aula.cadastroClientes.database.ConexaoDataBase;
import br.com.senac.aula.cadastroClientes.model.Client;
import br.com.senac.aula.cadastroClientes.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientesService {
    private static ConexaoDataBase conexaoDataBase = new ConexaoDataBase();

    public static List<Client> carregarCliente() throws SQLException, ClassNotFoundException {
        List<Client> out = new ArrayList<>();

        Connection conn = conexaoDataBase.getConnection();

        Statement sta = conn.createStatement();
        ResultSet resultSet = sta.executeQuery("SELECT * FROM public.clientes;");

        while(resultSet.next()){
            Client cli = new Client();
            cli.setIdCliente(resultSet.getInt(1));
            cli.setNomeCliente(resultSet.getString(2));
            cli.setDocumentoCliente(resultSet.getInt(3));
            cli.setRgCliente(resultSet.getString(4));
            cli.setEmailCliente(resultSet.getString(5));
            cli.setTelefoneCliente(resultSet.getString(6));
            out.add(cli);
        }

        return out;
    }

    public static void salvarCliente(Client cli){
        try{

            Connection conn = conexaoDataBase.getConnection();

            String insertSql = "insert into public.clientes (nome, email, documento, rg, telefone) values (?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(insertSql);

            ps.setString(1, cli.getNomeCliente());
            ps.setString(2, cli.getEmailCliente());
            ps.setString(3, Integer.toString(cli.getDocumentoCliente()));
            ps.setString(4, cli.getRgCliente());
            ps.setString(5, cli.getTelefoneCliente());

            ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean deletarCliente(int idCliente){
        try {
            Connection conn = conexaoDataBase.getConnection();

            String deleteSql = "delete from clientes where id = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, idCliente);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static boolean atualizarCliente(int idCliente, Client cli){
        try {
            Connection conn = conexaoDataBase.getConnection();

            String updateSql = "update clientes set rg = ?, email = ?, telefone = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(updateSql);


            ps.setString(1, cli.getRgCliente());

            ps.setString(2, cli.getEmailCliente());

            ps.setString(3, cli.getTelefoneCliente());

            ps.setInt(4, idCliente);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean buscarClienteByDocumento(String documento){
        try{
            Connection conn = conexaoDataBase.getConnection();

            String selectSql = "select id from clientes where documento = '" + documento + "'";

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
////////////////////////////////////////////////////

    public static boolean buscarIdCliente(Integer id_cliente){
        try{
            Connection conn = conexaoDataBase.getConnection();
            String sql = "UPDATE enderecos " +
                    "SET id_cliente = clientes.id " +
                    "FROM clientes " +
                    "WHERE enderecos.id_clientes = clientes.id";

            String selectSql = "SELECT * FROM clientes WHERE id = id";

            Statement sta = conn.createStatement();
            ResultSet rs = sta.executeQuery(selectSql);

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
