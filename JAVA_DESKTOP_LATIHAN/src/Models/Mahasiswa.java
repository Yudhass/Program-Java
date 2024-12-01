
package Models;

import java.io.Serializable;

public class Mahasiswa implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String nama;
    private String nim;
    private String alamat;

    public Mahasiswa() {
    }

    public Mahasiswa(int id, String nama, String nim, String alamat) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
}
