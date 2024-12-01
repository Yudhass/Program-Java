/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java_test;

import Database.Koneksi;
import Pages.Login;

/**
 *
 * @author User
 */
public class JAVA_TEST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Koneksi koneksi = new Koneksi();
        boolean cek = koneksi.TestKoneksi();
        if (!cek) {
            System.out.println("Koneksi Database ERROR");
        } else {
            Login login = new Login();
            login.setVisible(true);
        }
    }
    
}
