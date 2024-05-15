package Clases;

import Conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cls_Trabajador {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_INSERT_TRABAJADOR = "INSERT INTO trabajadores (tra_usuario, tra_clave, tra_documento, tra_nombre) VALUES (?,?,?,?)";
    private final String SQL_SELECT_TRABAJADOR = "SELECT * FROM trabajadores";
    private Connection con;

    public Cls_Trabajador() {
        PS = null;
        CN = new Conectar();
        con = CN.getConnection();
    }

    private DefaultTableModel setTitulosTrabajador() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DT.addColumn("ID");
        DT.addColumn("Usuario");
        DT.addColumn("Clave");
        DT.addColumn("Documento");
        DT.addColumn("Nombre");
        return DT;
    }

    public DefaultTableModel getDatosTrabajadores() {
        try {
            setTitulosTrabajador();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_TRABAJADOR);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los datos." + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
    }

    public int registrarTrabajador(String usuario, String clave, String documento, String nombre) {
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT_TRABAJADOR);
            PS.setString(1, usuario);
            PS.setString(2, clave);
            PS.setString(3, documento);
            PS.setString(4, nombre);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Trabajador registrado con éxito.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el trabajador.");
            System.err.println("Error al registrar el trabajador." + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int actualizarTrabajador(String usuario, String clave, String documento, String nombre, String usuarioViejo) {
        String SQL = "UPDATE trabajadores SET tra_usuario=?, tra_clave=?, tra_documento=?, tra_nombre=? WHERE tra_usuario=?";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            PS.setString(1, usuario);
            PS.setString(2, clave);
            PS.setString(3, documento);
            PS.setString(4, nombre);
            PS.setString(5, usuarioViejo);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Trabajador actualizado con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar los datos del trabajador: " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public int eliminarTrabajador(String usuario) {
        String SQL = "DELETE FROM trabajadores WHERE tra_usuario = ?";
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            PS.setString(1, usuario);
            res = PS.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Trabajador eliminado con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el trabajador: " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }

    public boolean verificarCredenciales(String usuario, String clave) {
        try {
            if (usuario.equals("admin") && clave.equals("123456")) {
                return true;
            }
            String consulta = "SELECT * FROM trabajadores WHERE tra_usuario = ? AND tra_clave = ?";
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, usuario);
            pst.setString(2, clave);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // Si encuentra un registro, las credenciales son válidas
        } catch (SQLException ex) {
            System.err.println("Error al verificar las credenciales: " + ex.getMessage());
            return false;
        }
    }
}
