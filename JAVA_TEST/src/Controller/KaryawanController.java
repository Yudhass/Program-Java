/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Database.Koneksi;
import Models.Karyawan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class KaryawanController {
    
    public Karyawan mahasiswa;
    public Koneksi koneksi;
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet resultSet;
    ArrayList<Karyawan> listKaryawan = new ArrayList<>();

    public KaryawanController() {
        this.koneksi = new Koneksi();
    }
    
    
    
    public Karyawan getDataKaryawan(String email){
        String kueri = "SELECT * FROM karyawan where email='" + email + "'";
        statement = null;
        resultSet = null;
        try {
            statement = koneksi.getKoneksi().createStatement();
            resultSet = statement.executeQuery(kueri);
            if (resultSet.next()) {
//                (int id, String nama, String alamat, String email, String password)
                Karyawan kar = new Karyawan(resultSet.getInt("id"), resultSet.getString("nama"), resultSet.getString("alamat"), resultSet.getString("email"), resultSet.getString("password"));
                return kar;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //fungsi notifikasi
    public void sukses(String message) {
        JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void error(String message) {
        JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.ERROR_MESSAGE);
    }
}
