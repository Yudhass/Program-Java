package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Koneksi {

    Connection koneksi = null;
    Statement statement;
    ResultSet result;
    String query;

    String jdbcURL = "jdbc:mysql://localhost:3306/latihan_java_dekstop";
    String user = "root";
    String password = "";

    public Koneksi() {
        OpenKoneksi();
    }

    public boolean TestKoneksi() {
        try {
            koneksi = DriverManager.getConnection(this.jdbcURL, this.user, this.password);
            System.out.println("Koneksi Berhasil");
            koneksi.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void OpenKoneksi() {
        try {
            koneksi = DriverManager.getConnection(this.jdbcURL, this.user, this.password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection getKoneksi(){
        return this.koneksi;
    }
    
    public void CloseKoneksi(){
        try {
            this.koneksi.close();
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
