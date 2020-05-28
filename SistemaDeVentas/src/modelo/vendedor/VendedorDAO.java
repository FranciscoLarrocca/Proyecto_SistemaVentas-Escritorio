package modelo.vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexion.Conexion;

public class VendedorDAO implements VendedorCRUD {

    Connection con;

    PreparedStatement ps;
    ResultSet rs;

    public Vendedor validarVendedor(String user, String dni) {

        Vendedor vd = new Vendedor();
        String query = "select * from vendedor where Usuario=? and Dni=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, dni);

            rs = ps.executeQuery();

            while (rs.next()) {
                vd.setId(rs.getInt(1));
                vd.setDni(rs.getString(2));
                vd.setNombre(rs.getString(3));
                vd.setTelefono(rs.getString(4));
                vd.setEstado(rs.getString(5));
                vd.setUser(rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + ex.getMessage());
        }
        return vd;
    }

    //CRUD: 
    @Override
    public List listarVendedores() {
        List<Vendedor> lista = new ArrayList();
        String query = "Select * from Vendedor";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Vendedor v = new Vendedor();

                v.setId(rs.getInt(1));
                v.setDni(rs.getNString(2));
                v.setNombre(rs.getString(3));
                v.setTelefono(rs.getString(4));
                v.setEstado(rs.getString(5));
                v.setUser(rs.getString(6));

                lista.add(v);
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int agregarVendedor(Vendedor v) {
        int r = 0;

        String insert = "insert into vendedor(dni, nombres, telefono, estado, usuario) values(?,?,?,?,?)";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(insert);
            
            ps.setObject(1, v.getDni());
            ps.setObject(2, v.getNombre());
            ps.setObject(3, v.getTelefono());
            ps.setObject(4, v.getEstado());
            ps.setObject(5, v.getUser());

            r = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    @Override
    public int actualizarVendedor(Vendedor v) {
        int r = 0;

        String update = "update vendedor set nombres=?, telefono=?, estado=?, Usuario=? WHERE idVendedor=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(update);

            ps.setObject(1, v.getNombre());
            ps.setObject(2, v.getTelefono());
            ps.setObject(3, v.getEstado());
            ps.setObject(4, v.getUser());

            ps.setObject(5, v.getId());

            r = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    @Override
    public void eliminarVendedor(int id) {
        String delete = "delete from vendedor where idVendedor=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(delete);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
    }

}
