
import Database.Koneksi;
import PAGES.JFrameDataMahasiswa;

public class JAVA_DESKTOP {

    public static void main(String[] args) {
        Koneksi koneksi = new Koneksi();
        boolean cek = koneksi.TestKoneksi();
        if (!cek) {
            System.out.println("Koneksi Database ERROR");
        } else {
            JFrameDataMahasiswa dataMahasiswa = new JFrameDataMahasiswa();
            dataMahasiswa.setVisible(true);
        }

    }

}
