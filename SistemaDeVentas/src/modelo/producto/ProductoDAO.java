package modelo.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import conexion.Conexion;

public class ProductoDAO implements ProductoCRUD {

    private Connection con;

    private PreparedStatement ps;
    private ResultSet rs;

    public Producto listarID(int id) {
        Producto p = new Producto();
        String query = "select * from producto where idProducto=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));

            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return p;
    }

    @Override
    public List listarProductos() {
        List<Producto> lista = new ArrayList();
        String query = "select * from producto";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();

                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));

                lista.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int agregarProducto(Producto p) {
        int r = 0;

        String insert = "insert into producto(nombres, precio, stock, estado) values(?,?,?,?)";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(insert);

            ps.setObject(1, p.getNombre());
            ps.setObject(2, p.getPrecio());
            ps.setObject(3, p.getStock());
            ps.setObject(4, p.getEstado());

            r = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    @Override
    public int actualizarProducto(Producto p) {
        int r = 0;
        String update = "update producto set nombres=?, precio=?, stock=?, estado=? WHERE idProducto=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(update);

            ps.setObject(1, p.getNombre());
            ps.setObject(2, p.getPrecio());
            ps.setObject(3, p.getStock());
            ps.setObject(4, p.getEstado());

            ps.setObject(5, p.getId());

            r = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    @Override
    public void eliminarProducto(int id) {
        String delete = "delete from producto where idProducto=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(delete);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
    }
    
    public int actualizarStock(int cantidad, int idProducto){
        int r = 0;
        String sql = "Update producto set stock=? where idProducto=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
                       
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
            
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        } 
        return r;
    }

}
