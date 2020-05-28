/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.ventas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import conexion.Conexion;
import modelo.detalleVentas.DetalleVentas;
import vista.VentasForm;

/**
 *
 * @author Usuario
 */
public class VentasDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    int r = 0;

    public String idVentas() {
        String idVenta = "";
        String sql = "select max(idventas) from ventas";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                idVenta = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return idVenta;
    }

    public int guardarVenta(Ventas v) {
        Ventas ventas = new Ventas();
        String sql = "insert into ventas(ventas_idCliente, ventas_idVendedor, numeroSerie, Fecha, Monto, Estado) values(?,?,?,?,?,?)";

        //Pasar String a Date:
        Date fecha = Date.valueOf(v.getFecha());

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdVendedor());
            ps.setString(3, v.getSerie());
            ps.setDate(4, fecha);
            ps.setDouble(5, v.getMonto());
            ps.setString(6, v.getEstado());

            r = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    public int guardarDetalleVentas(DetalleVentas dv) {
        String sql = "insert into detalle_ventas(detalleVentas_idVentas, detalleVentas_idProducto, cantidad, precioVenta) values(?,?,?,?)";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, dv.getIdVentas());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPrecio());

            r = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    public String generarNumeroSerie() {
        String serie = "";
        String sql = "select max(NumeroSerie) from ventas";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                serie = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }

        return serie;
    }
}
