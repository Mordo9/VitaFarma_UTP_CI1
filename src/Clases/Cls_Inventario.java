package Clases;

import Conexion.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;



public class Cls_Inventario {

    private PreparedStatement PS;
    private ResultSet RS;
    private final Conectar CN;
    private DefaultTableModel DT;
    private final String SQL_SELECT_INVENTARIO = "SELECT inv_pro_codigo, pro_descripcion, inv_entradas, inv_salidas, inv_stock, inv_pro_categoria, pro_categoria FROM inventario INNER JOIN producto ON inv_pro_codigo = pro_codigo";

    public Cls_Inventario() {
        PS = null;
        CN = new Conectar();
    }

    private DefaultTableModel setTitulosInventario() {
        DT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        DT.addColumn("Código");
        DT.addColumn("Descripción");
        DT.addColumn("Entrada");
        DT.addColumn("Salida");
        DT.addColumn("Stock");
        DT.addColumn("Categoría");
        return DT;
    }

    public DefaultTableModel getDatosInventario() {
        try {
            setTitulosInventario();
            PS = CN.getConnection().prepareStatement(SQL_SELECT_INVENTARIO);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()) {
                fila[0] = RS.getString(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getInt(5);
                fila[5] = RS.getString(6);
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

    public List<String> verificarBajoStock(int stockMinimo) {
        List<String> productosBajoStock = new ArrayList<>();

        for (int i = 0; i < DT.getRowCount(); i++) {
            int stockActual = (int) DT.getValueAt(i, 4); // Obtener el valor de la columna "Stock"

            // Verificar si el stock es menor que el mínimo
            if (stockActual < stockMinimo) {
                String codigoProducto = (String) DT.getValueAt(i, 0); // Obtener el código del producto
                productosBajoStock.add(codigoProducto);
            }
        }

        return productosBajoStock;
    }
}
