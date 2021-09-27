package mx.edu.utez.dataBase;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/examen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Gearsofwar_2002");
    }

    public static void main(String[] args) {
        try{
            Connection con = ConnectionMysql.getConnection();
            System.out.println("Conexion exitosa!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
