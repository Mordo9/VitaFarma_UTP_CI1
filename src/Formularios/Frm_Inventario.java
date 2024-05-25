package Formularios;

import Clases.Cls_Inventario;
import javax.swing.table.TableColumnModel;
import javax.swing.JOptionPane;
import java.util.List;

public class Frm_Inventario extends javax.swing.JInternalFrame {

    private final Cls_Inventario CP;
    TableColumnModel columnModel;
    public static int enviar = 0;
    int num = 0;

    public Frm_Inventario() {
        initComponents();
        CP = new Cls_Inventario();
        columnModel = jtb_inventario.getColumnModel();
        listar();
    }

    private void listar() {
        jtb_inventario.setModel(CP.getDatosInventario());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_inventario = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtb_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_inventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_inventario);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Inventario de Productos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel7.setText("Entrada, Salida y Stock de Productos.");

        jButton1.setText("Verificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtb_inventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_inventarioMouseClicked

    }//GEN-LAST:event_jtb_inventarioMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listar();
        int stockMinimo = 40; // Puedes ajustar este valor según tus necesidades
        List<String> productosBajoStock = CP.verificarBajoStock(stockMinimo);

        if (!productosBajoStock.isEmpty()) {
            // Construir el mensaje con los productos bajo stock
            StringBuilder mensaje = new StringBuilder("Bajo stock para los productos con códigos: \n");
            for (String codigoProducto : productosBajoStock) {
                mensaje.append(codigoProducto).append("\n");
            }

            // Mostrar el mensaje centrado
            JOptionPane.showMessageDialog(this, mensaje.toString(), "Stock Bajo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar mensaje indicando que no hay productos bajo stock
            JOptionPane.showMessageDialog(this, "No hay productos bajo en stock.", "Stock Normal", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtb_inventario;
    // End of variables declaration//GEN-END:variables
}
