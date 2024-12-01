package Controller;

import Database.Koneksi;
import Models.Mahasiswa;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MahasiswaController {

    public Mahasiswa mahasiswa;
    public Koneksi koneksi;
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet resultSet;
    ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();

    public MahasiswaController() {
        this.koneksi = new Koneksi();
    }

    public int addMahasiswa(String nama, String nim, String alamat) {
        String kueri = "INSERT INTO mahasiswa (nama,nim,alamat) VALUES (?,?,?)";
        prepareStatement = null;
        int result = 0;
        try {
            prepareStatement = koneksi.getKoneksi().prepareStatement(kueri);
            prepareStatement.setString(1, nama);
            prepareStatement.setString(2, nim);
            prepareStatement.setString(3, alamat);
            result = prepareStatement.executeUpdate();
            this.sukses("Data mahasiswa berhasil ditambahkan");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.error(e.getMessage());
        }
        return result;
    }

    public int updateMahasiswa(String id, String nama, String nim, String alamat) {
        int res = 0;
        mahasiswa = this.cekData(id);
        if (mahasiswa == null) {
            return res;
        } else {
            prepareStatement = null;
            String kueri = "UPDATE mahasiswa SET nama = ?, nim = ?, alamat = ? WHERE id = ?";
            try {
                prepareStatement = koneksi.getKoneksi().prepareStatement(kueri);
                prepareStatement.setString(1, nama);
                prepareStatement.setString(2, nim);
                prepareStatement.setString(3, alamat);
                prepareStatement.setInt(4, mahasiswa.getId());
                res = prepareStatement.executeUpdate();
                prepareStatement.close();
            } catch (SQLException | HeadlessException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return res;
    }

    public int deleteMahasiswa(String id) {
        int res = 0;
        mahasiswa = this.cekData(id);
        if (mahasiswa == null) {
            return res;
        } else {
            prepareStatement = null;
            String kueri = "DELETE FROM mahasiswa WHERE id = ?";
            try {
                prepareStatement = koneksi.getKoneksi().prepareStatement(kueri);
                prepareStatement.setInt(1, mahasiswa.getId());
                res = prepareStatement.executeUpdate();
                prepareStatement.close();
            } catch (SQLException | HeadlessException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return res;
    }

    public ArrayList<Mahasiswa> cariMahasiswa(String keyword) {
        listMahasiswa.clear();
        statement = null;
        resultSet = null;
        String kueri = "SELECT * FROM mahasiswa WHERE nim LIKE ? OR nama LIKE ? OR alamat LIKE ?;";
        try {
            prepareStatement = koneksi.getKoneksi().prepareStatement(kueri);
            prepareStatement.setString(1, "%" + keyword + "%");
            prepareStatement.setString(2, "%" + keyword + "%");
            prepareStatement.setString(3, "%" + keyword + "%");
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Mahasiswa mhs = new Mahasiswa(
                        resultSet.getInt("id"),
                        resultSet.getString("nim"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat")
                );
                listMahasiswa.add(mhs);
            }
            resultSet.close();
            prepareStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listMahasiswa;
    }

    public Mahasiswa cekData(String id) {
        String kueri = "SELECT * FROM mahasiswa where id='" + id + "'";
        statement = null;
        resultSet = null;
        try {
            statement = koneksi.getKoneksi().createStatement();
            resultSet = statement.executeQuery(kueri);
            if (resultSet.next()) {
                Mahasiswa mhs = new Mahasiswa(resultSet.getInt("id"), resultSet.getString("nama"), resultSet.getString("nim"), resultSet.getString("alamat"));
                return mhs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Mahasiswa> getDataMahasiswa() throws SQLException {
        listMahasiswa.clear(); // Pastikan list di-clear sebelum digunakan
        statement = null;
        resultSet = null;

        try {
            statement = koneksi.getKoneksi().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mahasiswa");

            // Tidak perlu resultSet.wasNull(), langsung iterasi
            while (resultSet.next()) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        resultSet.getInt("id"),
                        resultSet.getString("nama"),
                        resultSet.getString("nim"),
                        resultSet.getString("alamat")
                );
                listMahasiswa.add(mahasiswa);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Berikan penanganan kesalahan yang lebih baik
        } finally {
            statement.close();
        }

        return listMahasiswa;
    }

    //fungsi notifikasi
    public void sukses(String message) {
        JOptionPane.showMessageDialog(null, message, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void error(String message) {
        JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.ERROR_MESSAGE);
    }

}
