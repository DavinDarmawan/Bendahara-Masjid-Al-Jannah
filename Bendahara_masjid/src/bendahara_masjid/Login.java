/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bendahara_masjid;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Login extends javax.swing.JPanel {

    /**
     * Creates new form Login
     */
    
    private JPanel mainPanel;
  public Login(JPanel panel) {
    initComponents();
    this.mainPanel = panel;
    this.mainPanel.setLayout(new java.awt.BorderLayout());

    // background dark
    this.setBackground(new java.awt.Color(30, 30, 30));

    // judul
    jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));

    // step 3: label username & password
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    jLabel1.setForeground(new java.awt.Color(200, 200, 200));

    jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    jLabel2.setForeground(new java.awt.Color(200, 200, 200));
    
    // step 4: txtUsername dark
    txtUsername.setBackground(new java.awt.Color(50, 50, 50));
    txtUsername.setForeground(new java.awt.Color(255, 255, 255));
    txtUsername.setCaretColor(new java.awt.Color(255, 255, 255));
    txtUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    txtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)));
    
    txtPassword.setBackground(new java.awt.Color(50, 50, 50));
    txtPassword.setForeground(new java.awt.Color(255, 255, 255));
    txtPassword.setCaretColor(new java.awt.Color(255, 255, 255));
    txtPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)));
    
    // step 6: checkbox "Lihat Password"
    cek.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    cek.setForeground(new java.awt.Color(200, 200, 200));
    cek.setBackground(new java.awt.Color(30, 30, 30));
    
    btnLogin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    btnLogin.setBackground(new java.awt.Color(70, 130, 180)); // steel blue
    btnLogin.setForeground(java.awt.Color.WHITE);
    btnLogin.setFocusPainted(false);
    btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        cek = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Password:");

        jLabel2.setText("Username:");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        cek.setText("Lihat Password");
        cek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setText("Login Aplikasi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cek, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(btnLogin))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel3)))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cek, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogin)
                .addContainerGap(297, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
          String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = koneksidb.getKoneksi();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Jika data ditemukan, login berhasil
                JOptionPane.showMessageDialog(this, "Login Berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                // Ganti panel ke halaman utama (Home)
                mainPanel.removeAll();
                mainPanel.add(new Home(mainPanel));
                mainPanel.revalidate();
                mainPanel.repaint();
                
            } else {
                // Jika tidak ada data yang cocok
                JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    private void checkLihatPasswordActionPerformed(java.awt.event.ActionEvent evt) {                                                   
       
    }//GEN-LAST:event_btnLoginActionPerformed

    private void cekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekActionPerformed
        // TODO add your handling code here:
         // TODO add your handling code here:
        // Cek apakah checkbox sedang dicentang atau tidak
    if (cek.isSelected()) {
        // Jika dicentang, tampilkan password
        // (char) 0 akan membuat karakter terlihat
        txtPassword.setEchoChar((char) 0); 
    } else {
        // Jika tidak dicentang, sembunyikan lagi passwordnya
        // Karakter '•' (bullet) adalah standar untuk password
        txtPassword.setEchoChar('•'); 
    }
    }//GEN-LAST:event_cekActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox cek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
