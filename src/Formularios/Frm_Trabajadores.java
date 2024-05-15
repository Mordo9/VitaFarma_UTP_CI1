package Formularios;

import Clases.Cls_Trabajador;
import javax.swing.JOptionPane;

import javax.swing.table.TableColumnModel;

public class Frm_Trabajadores extends javax.swing.JInternalFrame {

    private final Cls_Trabajador CP;
    TableColumnModel columnModel;
    int num = 0;

    public Frm_Trabajadores() {
        initComponents();
        CP = new Cls_Trabajador();
        columnModel = jtb_trabajador.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);

//
    }

    private void listar(){
        jtb_trabajador.setModel(CP.getDatosTrabajadores());
        columnModel.getColumn(1).setPreferredWidth(600);
    }
    
    private void iniciar(){
        txt_dni.setEnabled(true);
        txt_nclave.setEnabled(true);
        txt_nusuario.setEnabled(true);
        txt_nombreusr.setEnabled(true);
    }
    
    private void activar(){
        txt_nusuario.setEnabled(true);
        txt_nclave.setEnabled(true);
        txt_dni.setEnabled(true);
        txt_nombreusr.setEnabled(true);
        txt_nusuario.requestFocus();
        
    }
    
    private void limpiar(){
        txt_nusuario.setText("");
        txt_nclave.setText("");
        txt_dni.setText("");
        txt_nombreusr.setText("");
        txt_nombreusr.requestFocus();
        jtb_trabajador.clearSelection();
    }
    
    private void guardar() {
    String usuario = txt_nusuario.getText();
    String clave = txt_nclave.getText();
    String dni = txt_dni.getText();
    String nombreusr = txt_nombreusr.getText();

    if (num == 0) {
        int respuesta = CP.registrarTrabajador(usuario, clave, dni, nombreusr);
        if (respuesta > 0) {
            listar();
            limpiar();
            iniciar();
            bt_actualizar.setEnabled(false);
        }
    } else {
        int row = jtb_trabajador.getSelectedRow();
        String usuarioSeleccionado = jtb_trabajador.getValueAt(row, 1).toString(); // Obtener el usuario del trabajador seleccionado en la tabla

        int respuesta = CP.actualizarTrabajador(usuario, clave, dni, nombreusr, usuarioSeleccionado);
        if (respuesta > 0) {
            listar();
            limpiar();
            iniciar();
            num = 0;
        }
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nusuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nclave = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nombreusr = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_dni = new javax.swing.JTextField();
        bt_nuevo = new javax.swing.JButton();
        bt_guardar = new javax.swing.JButton();
        bt_actualizar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_trabajador = new javax.swing.JTable();

        setClosable(true);
        setTitle("Trabajadores");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1041, 563));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Usuario a dar*");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 169, -1));
        jPanel1.add(txt_nusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 169, 28));

        jLabel2.setText("Clave a dar*");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 169, -1));
        jPanel1.add(txt_nclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 169, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("REGISTRO USUARIOS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 18, -1, -1));

        jLabel4.setText("Nombre Completo*");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));
        jPanel1.add(txt_nombreusr, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 311, 28));

        jLabel5.setText("Documento de identificación*");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));
        jPanel1.add(txt_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 196, 30));

        bt_nuevo.setText("Nuevo");
        bt_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nuevoActionPerformed(evt);
            }
        });
        jPanel1.add(bt_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, -1, -1));

        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, -1, -1));

        bt_actualizar.setText("Editar");
        bt_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actualizarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, -1, -1));

        bt_eliminar.setText("Borrar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 190, -1, -1));

        jtb_trabajador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtb_trabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_trabajadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_trabajador);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 228, 917, 249));

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

    private void jtb_trabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_trabajadorMouseClicked
        bt_actualizar.setEnabled(true);
        bt_eliminar.setEnabled(true);
        
        int row = jtb_trabajador.getSelectedRow(); 
        txt_nusuario.setText(jtb_trabajador.getValueAt(row, 0).toString());
        txt_nclave.setText(jtb_trabajador.getValueAt(row, 1).toString());
        txt_dni.setText(jtb_trabajador.getValueAt(row, 2).toString());
        txt_nombreusr.setText(jtb_trabajador.getValueAt(row, 3).toString());
    }//GEN-LAST:event_jtb_trabajadorMouseClicked

    private void bt_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoActionPerformed
        activar();
        limpiar();
        bt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevoActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = jtb_trabajador.getSelectedRowCount();
        if (fila < 1){
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla");
        }
        else{
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar?","Eliminar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(resp==0){
                if (CP.eliminarTrabajador(jtb_trabajador.getValueAt(jtb_trabajador.getSelectedRow(), 1).toString()) > 0) {
                    listar();
                    limpiar();
                    bt_eliminar.setEnabled(false);
                    bt_actualizar.setEnabled(false);
                    bt_guardar.setEnabled(false);
                }
            }
        }
       
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualizarActionPerformed
        num = 1;
        activar();
        bt_actualizar.setEnabled(false);
        bt_guardar.setEnabled(true);
        bt_eliminar.setEnabled(false);
    }//GEN-LAST:event_bt_actualizarActionPerformed

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_bt_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bt_actualizar;
    javax.swing.JButton bt_eliminar;
    javax.swing.JButton bt_guardar;
    javax.swing.JButton bt_nuevo;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JPanel jPanel1;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable jtb_trabajador;
    javax.swing.JTextField txt_dni;
    javax.swing.JTextField txt_nclave;
    javax.swing.JTextField txt_nombreusr;
    javax.swing.JTextField txt_nusuario;
    // End of variables declaration//GEN-END:variables
}
