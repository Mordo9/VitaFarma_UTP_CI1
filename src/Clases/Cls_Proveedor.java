package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Proveedor {
    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_PROVEEDOR = "INSERT INTO proveedor (nombre, ruc, direccion, telefono, correo) VALUES (?,?,?,?,?)";
    private final String SQL_SELECT_PROVEEDORES = "SELECT * FROM proveedor";
    
    public Cls_Proveedor() {
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulosProveedor() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DT.addColumn("ID");
        DT.addColumn("Nombre");
        DT.addColumn("RUC");
        DT.addColumn("Dirección");
        DT.addColumn("Teléfono");
        DT.addColumn("Correo");
        return DT;
    }
    
    public DefaultTableModel getDatosProveedores() {
        try {
            setTitulosProveedor();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_PROVEEDORES);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()) {
                fila[0] = RS.getInt("id");
                fila[1] = RS.getString("nombre");
                fila[2] = RS.getString("ruc");
                fila[3] = RS.getString("direccion");
                fila[4] = RS.getString("telefono");
                fila[5] = RS.getString("correo");
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }
    
    public int registrarProveedor(String nombre, String ruc, String direccion, String telefono, String correo) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_PROVEEDOR);
            PS.setString(1, nombre);
            PS.setString(2, ruc);
            PS.setString(3, direccion);
            PS.setString(4, telefono);
            PS.setString(5, correo);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Proveedor registrado con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el proveedor.");
            System.err.println("Error al registrar el proveedor: " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }
}
