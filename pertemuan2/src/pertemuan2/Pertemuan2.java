/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pertemuan2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author VM2
 */
public class Pertemuan2 {

    Connection conn = null;
    String url = "jdbc:mysql://localhost/sewa_ruang_51422643";
    String user = "root";
    String password = "";
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pertemuan2 pert2 = new Pertemuan2();

        pert2.insertData();
        pert2.showData();
        pert2.updateData();
        pert2.deleteData();
    }

    public void insertData() {
        System.out.println("**MASUKKAN DATA**");
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO ruangan(id_ruang,jenis_ruang,harga_sewa) VALUES(?,?,?)";
            pst = conn.prepareStatement(query);
            for (int i = 1; i <= 10; i++) {
                pst.setInt(1, i);
                pst.setString(2, "Ruang Ke-" + i);
                pst.setFloat(3, new Float(0.23 * i));
                pst.executeUpdate();
            }
            System.out.println("==============================");
            System.out.println("**BERHASIL MASUKKAN DATA**");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("==============================");
        }
        try {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showData() {
        System.out.println("**TAMPILKAN DATA**");
        System.out.println("==============================");
        System.out.println("id_ruang" + "\t" + "jenis_ruang" + "\t" + "\t" + "\t" + "harga_sewa");
        try {
            conn = DriverManager.getConnection(url, user, password);
            pst = conn.prepareStatement("SELECT * FROM ruangan");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("id_ruang"));
                System.out.print("\t" + "\t");
                System.out.print(rs.getString("jenis_ruang"));
                System.out.print("\t" + "\t");
                System.out.println(rs.getFloat("harga_sewa"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("==============================");
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void updateData() {
        System.out.println("**EDIT DATA**");
        System.out.println("edit data id : 3");
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "UPDATE ruangan SET harga_sewa=? WHERE id_ruang=3";
            pst = conn.prepareStatement(query);
            pst.setFloat(1, new Float(3.0));
            pst.executeUpdate();
            System.out.println("==============================");

            System.out.println("**BERHASIL EDIT DATA**");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("==============================");
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteData() {
        System.out.println("**HAPUS DATA");
        System.out.println("hapus data id : 2");
        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM ruangan WHERE id_ruang=2";
            pst = conn.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("==============================");
            System.out.println("**BERHASIL HAPUS DATA**");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("==============================");
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
