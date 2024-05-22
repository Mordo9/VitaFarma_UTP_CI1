package Clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cls_Vencimiento {

    private Connection con;

    public Cls_Vencimiento(Connection con) {
        this.con = con;
    }

    public boolean hayProductosCercanosAVencimiento(Date fecha) {
        String sql = "SELECT COUNT(*) FROM producto WHERE pro_fv <= DATE_ADD(?, INTERVAL 2 MONTH)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, fecha);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar fechas de vencimiento: " + ex.getMessage());
        }
        return false;
    }
}
