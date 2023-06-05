package br.com.senac.aula.cadastroClientes.services;

import br.com.senac.aula.cadastroClientes.database.ConexaoDataBase;
import br.com.senac.aula.cadastroClientes.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoService {

    private static ConexaoDataBase conexaoDataBase = new ConexaoDataBase();

    public static List<Endereco> carregarEndereco() throws SQLException, ClassNotFoundException {
        List<Endereco> out = new ArrayList<>();

        Connection conn = conexaoDataBase.getConnection();

        Statement sta = conn.createStatement();
        ResultSet resultSet = sta.executeQuery("SELECT * FROM public.enderecos;");

        while(resultSet.next()){
            Endereco end = new Endereco();
            end.setCodigoClienteEndereco(resultSet.getInt(1));
            end.setCepEndereco(resultSet.getString(2));
            end.setBairroEndereco(resultSet.getString(3));
            end.setNumeroEndereco(resultSet.getString(4));
            end.setCidadeEndereco(resultSet.getString(5));
            end.setEstadoEndereco(resultSet.getString(6));
            out.add(end);
        }

        return out;
    }
    public static void salvarEndereco(Endereco end){
        try{

            Connection conn = conexaoDataBase.getConnection();

            String insertSql = "insert into public.enderecos (cep, bairro, numero, cidade, estado, id_cliente) values (?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(insertSql);

            ps.setString(1, end.getCepEndereco());
            ps.setString(2, end.getBairroEndereco());
            ps.setString(3, end.getNumeroEndereco());
            ps.setString(4, end.getCidadeEndereco());
            ps.setString(5, end.getEstadoEndereco());
            ps.setInt(6, end.getCodigoClienteEndereco());

            ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean atualizarEndereco(int index2, Endereco end){
        try {
            Connection conn = conexaoDataBase.getConnection();

            String updateSql = "update enderecos set cep = ?, bairro = ?, numero = ?, cidade = ?, estado = ?  where id_cliente = ?";

            PreparedStatement ps = conn.prepareStatement(updateSql);

            ps.setString(1, end.getCepEndereco());
            ps.setString(2, end.getBairroEndereco());
            ps.setString(3, end.getNumeroEndereco());
            ps.setString(4, end.getCidadeEndereco());
            ps.setString(5, end.getEstadoEndereco());
            ps.setInt(6, index2);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public static boolean deletarEndereco(int codigoClienteEndereco){
        try {
            Connection conn = conexaoDataBase.getConnection();

            String deleteSql = "delete from enderecos where id_cliente = ?";

            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setInt(1, codigoClienteEndereco);

            return ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean buscarEndereco(String codigoCliente){
        try{
            Connection conn = conexaoDataBase.getConnection();

            String selectSql = "SELECT id_cliente FROM enderecos WHERE id_cliente = ?";
            PreparedStatement ps = conn.prepareStatement(selectSql);
            ps.setInt(1, Integer.parseInt(codigoCliente));

            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
