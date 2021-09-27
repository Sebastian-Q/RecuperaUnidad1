package mx.edu.utez.server;

import mx.edu.utez.dataBase.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Handler {

    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;


    public boolean createUser (String name, String lastname, String email, String password, int status){
        boolean state = false;
        try{
            con = ConnectionMysql.getConnection();
            String query = "INSERT INTO user(name, lastname, email, password, status, date_registered) VALUES (?, ?, ?, ?, ?, now());";
            pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);
            pstm.setInt(5,status);
            state = pstm.executeUpdate() == 1;
            rs = pstm.getGeneratedKeys();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }

    public List<usuario> findAll(){
        List<usuario> ListUser = new ArrayList<>();
        try {
            con = ConnectionMysql.getConnection();
            String query = "SELECT id, name, lastname, email, password, date_registered, status FROM user;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                usuario usuario = new usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setName(rs.getString("name"));
                usuario.setLastname(rs.getString("lastname"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setDate_registered(rs.getString("date_registered"));
                usuario.setStatus(rs.getInt("status"));
                ListUser.add(usuario);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return ListUser;
    }

    public boolean update(int id, String name, String lastname, String email, String password, int status){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query ="UPDATE `user` SET name = ?, lastname = ?, email = ?, password = ?, status = ? where id=?; ";
            pstm = con.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);
            pstm.setInt(5, status);
            pstm.setInt(6, id);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
            return state;
    }

    public boolean delete(int id){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query = "DELETE FROM user WHERE id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,id);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }


    public void closeConnection(){
        try {
            if (con != null){
                con.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();

        }
    }

}
