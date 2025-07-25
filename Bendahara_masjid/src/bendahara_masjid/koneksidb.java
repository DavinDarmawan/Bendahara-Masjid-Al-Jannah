/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bendahara_masjid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class koneksidb {
      private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
               String url = "jdbc:mysql://localhost:3306/db_bendaharamasjid";
                String user = "root";
                String password = "";

                
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println("Koneksi ke database gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}
