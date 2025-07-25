/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bendahara_masjid;

/**
 *
 * @author ASUS
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;

public class LogData extends javax.swing.JPanel {

    /**
     * Creates new form LogData
     */
    private javax.swing.JPanel mainPanel;
    public LogData(JPanel panel) {
        initComponents();
        this.mainPanel = panel;
        tampilkanLogData(null, null); 
    }
    
   private void tampilkanLogData(Integer tahun, Integer bulan) {
    // Bagian 1: Mengisi tabel log utama (tabelLog)
    DefaultTableModel logModel = new DefaultTableModel();
    logModel.addColumn("Tanggal");
    logModel.addColumn("Jenis Transaksi");
    logModel.addColumn("Deskripsi");
    logModel.addColumn("Masuk (Rp)");
    logModel.addColumn("Keluar (Rp)");
    tabelLog.setModel(logModel);

    try {
        Connection conn = koneksidb.getKoneksi(); // Pastikan nama kelas koneksi benar
        String sql;
        
        // Query untuk data detail (log)
        if (tahun == null || bulan == null) {
            sql = "SELECT tanggal, 'Pemasukan' AS jenis, sumber AS deskripsi, jumlah AS masuk, 0 AS keluar FROM pemasukan UNION ALL SELECT tanggal, 'Pengeluaran' AS jenis, kegiatan AS deskripsi, 0 AS masuk, jumlah AS keluar FROM pengeluaran ORDER BY tanggal DESC";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                logModel.addRow(new Object[]{res.getString("tanggal"), res.getString("jenis"), res.getString("deskripsi"), res.getBigDecimal("masuk"), res.getBigDecimal("keluar")});
            }
        } else {
            sql = "SELECT tanggal, 'Pemasukan' AS jenis, sumber AS deskripsi, jumlah AS masuk, 0 AS keluar FROM pemasukan WHERE YEAR(tanggal) = ? AND MONTH(tanggal) = ? UNION ALL SELECT tanggal, 'Pengeluaran' AS jenis, kegiatan AS deskripsi, 0 AS masuk, jumlah AS keluar FROM pengeluaran WHERE YEAR(tanggal) = ? AND MONTH(tanggal) = ? ORDER BY tanggal DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tahun); ps.setInt(2, bulan); ps.setInt(3, tahun); ps.setInt(4, bulan);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                logModel.addRow(new Object[]{res.getString("tanggal"), res.getString("jenis"), res.getString("deskripsi"), res.getBigDecimal("masuk"), res.getBigDecimal("keluar")});
            }
        }
        
        // ===================================================================
        // Bagian 2: Mengisi tabel ringkasan (uang)
        // ===================================================================
        DefaultTableModel summaryModel = new DefaultTableModel();
        summaryModel.addColumn("Total Pemasukan");
        summaryModel.addColumn("Total Pengeluaran");
        summaryModel.addColumn("Saldo Akhir");
        
        String sqlSummary;
        PreparedStatement psSummary;
        
        if (tahun == null || bulan == null) {
            // Query untuk total keseluruhan
            sqlSummary = "SELECT (SELECT IFNULL(SUM(jumlah), 0) FROM pemasukan) AS total_masuk, (SELECT IFNULL(SUM(jumlah), 0) FROM pengeluaran) AS total_keluar";
            psSummary = conn.prepareStatement(sqlSummary);
        } else {
            // Query untuk total berdasarkan filter
            sqlSummary = "SELECT (SELECT IFNULL(SUM(jumlah), 0) FROM pemasukan WHERE YEAR(tanggal) = ? AND MONTH(tanggal) = ?) AS total_masuk, (SELECT IFNULL(SUM(jumlah), 0) FROM pengeluaran WHERE YEAR(tanggal) = ? AND MONTH(tanggal) = ?) AS total_keluar";
            psSummary = conn.prepareStatement(sqlSummary);
            psSummary.setInt(1, tahun); psSummary.setInt(2, bulan); psSummary.setInt(3, tahun); psSummary.setInt(4, bulan);
        }

        ResultSet rsSummary = psSummary.executeQuery();
        if (rsSummary.next()) {
            java.math.BigDecimal totalMasuk = rsSummary.getBigDecimal("total_masuk");
            java.math.BigDecimal totalKeluar = rsSummary.getBigDecimal("total_keluar");
            java.math.BigDecimal saldoAkhir = totalMasuk.subtract(totalKeluar);
            
            // Tambahkan satu baris ringkasan ke model tabel 'uang'
            summaryModel.addRow(new Object[]{totalMasuk, totalKeluar, saldoAkhir});
        }
        
        // Atur model ke tabel 'uang'
        uang.setModel(summaryModel);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelLog = new javax.swing.JTable();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        yearChooser = new com.toedter.calendar.JYearChooser();
        btnFilter = new javax.swing.JButton();
        btnTampilkanSemua = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        pdf = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        uang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 250, 240));

        tabelLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelLog);

        monthChooser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        yearChooser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btnFilter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnTampilkanSemua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTampilkanSemua.setText("Full");
        btnTampilkanSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilkanSemuaActionPerformed(evt);
            }
        });

        Home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        pdf.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pdf.setText("Cetak PDF");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        uang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(uang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("LAPORAN TRANSAKSI");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("--------------------------------------------------------------------------------");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText(">Rekapitulasi Keuangan---------------------------------------------------");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText(">Riwayat Transaksi--------------------------------------------------------");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFilter)
                .addGap(18, 18, 18)
                .addComponent(btnTampilkanSemua)
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pdf)
                                .addGap(18, 18, 18)
                                .addComponent(Home))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(163, 163, 163)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel7))))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFilter)
                        .addComponent(btnTampilkanSemua)))
                .addGap(40, 40, 40)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home)
                    .addComponent(pdf))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        int tahun = yearChooser.getYear();
        // JMonthChooser 0-indexed (Jan=0), SQL 1-indexed (Jan=1), jadi tambah 1
        int bulan = monthChooser.getMonth() + 1; 
        tampilkanLogData(tahun, bulan);
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnTampilkanSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanSemuaActionPerformed
        // TODO add your handling code here:
        tampilkanLogData(null, null);
    }//GEN-LAST:event_btnTampilkanSemuaActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.add(new Home(mainPanel));
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_HomeActionPerformed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed
        // TODO add your handling code here:
    javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
    fileChooser.setDialogTitle("Simpan Laporan sebagai PDF");
    fileChooser.setSelectedFile(new java.io.File("Laporan Kas Masjid.pdf"));
    
    int userSelection = fileChooser.showSaveDialog(this);
    
    if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
        java.io.File fileToSave = fileChooser.getSelectedFile();
        
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(fileToSave));
            document.open();
            
            // --- Menambahkan Judul Laporan ---
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("Laporan Kas Masjid", titleFont);
            title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(title);
            document.add(new com.itextpdf.text.Paragraph(" "));

            // --- Membuat Tabel Log Transaksi (tabelLog) ---
            com.itextpdf.text.pdf.PdfPTable logTable = new com.itextpdf.text.pdf.PdfPTable(tabelLog.getColumnCount());
            logTable.setWidthPercentage(100);

            // Menambahkan Header Tabel Log
            for (int i = 0; i < tabelLog.getColumnCount(); i++) {
                com.itextpdf.text.pdf.PdfPCell headerCell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(tabelLog.getColumnName(i)));
                headerCell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(new com.itextpdf.text.BaseColor(200, 200, 200));
                logTable.addCell(headerCell);
            }

            // Menambahkan Isi Tabel Log
            for (int rows = 0; rows < tabelLog.getRowCount(); rows++) {
                for (int cols = 0; cols < tabelLog.getColumnCount(); cols++) {
                    Object cellValue = tabelLog.getValueAt(rows, cols);
                    logTable.addCell(cellValue != null ? cellValue.toString() : "");
                }
            }
            
            document.add(logTable);
            
            // ===================================================================
            // BAGIAN BARU: Tambahkan tabel ringkasan (uang)
            // ===================================================================
            document.add(new com.itextpdf.text.Paragraph(" ")); // Spasi pemisah
            
            com.itextpdf.text.Font summaryTitleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Paragraph summaryTitle = new com.itextpdf.text.Paragraph("Ringkasan Keuangan", summaryTitleFont);
            summaryTitle.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
            document.add(summaryTitle);
            document.add(new com.itextpdf.text.Paragraph(" ")); // Spasi

            // Membuat Tabel Ringkasan
            com.itextpdf.text.pdf.PdfPTable summaryTable = new com.itextpdf.text.pdf.PdfPTable(uang.getColumnCount());
            summaryTable.setWidthPercentage(100);

            // Menambahkan Header Tabel Ringkasan
            for (int i = 0; i < uang.getColumnCount(); i++) {
                com.itextpdf.text.pdf.PdfPCell headerCell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Phrase(uang.getColumnName(i)));
                headerCell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(new com.itextpdf.text.BaseColor(200, 200, 200));
                summaryTable.addCell(headerCell);
            }

            // Menambahkan Isi Tabel Ringkasan (hanya 1 baris)
            for (int rows = 0; rows < uang.getRowCount(); rows++) {
                for (int cols = 0; cols < uang.getColumnCount(); cols++) {
                    Object cellValue = uang.getValueAt(rows, cols);
                    summaryTable.addCell(cellValue != null ? cellValue.toString() : "");
                }
            }

            document.add(summaryTable);
            // ===================================================================

            document.close();
            
            JOptionPane.showMessageDialog(this, "Laporan PDF berhasil dibuat!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal membuat PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    }//GEN-LAST:event_pdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnTampilkanSemua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private javax.swing.JButton pdf;
    private javax.swing.JTable tabelLog;
    private javax.swing.JTable uang;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
