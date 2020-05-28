package modelo.cliente;

import modelo.cliente.ClienteCRUD;
import modelo.cliente.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import conexion.Conexion;

public class ClienteDAO implements ClienteCRUD {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Cliente listarID(String dni) {
        Cliente c = new Cliente();
        String query = "select * from cliente where dni=?";
        
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(query);
            ps.setString(1, dni);
            rs = ps.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEstado(rs.getString(5));

            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return c;
    }

    @Override
    public List listar() {
        List<Cliente> lista = new ArrayList();
        String query = "select * from cliente";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();

                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEstado(rs.getString(5));

                lista.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public int actualizarCliente(Cliente c) {
        int r = 0;
        String update = "update cliente set dni=?, nombres=?, direccion=?, estado=? WHERE idCliente=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(update);

            ps.setObject(1, c.getDni());
            ps.setObject(2, c.getNombre());
            ps.setObject(3, c.getDireccion());
            ps.setObject(4, c.getEstado());

            ps.setObject(5, c.getId());

            r = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

    @Override
    public void eliminarCliente(int id) {
        String delete = "delete from cliente where idCliente=?";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(delete);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
    }

    public int agregarCliente(Cliente c) {
        int r = 0;

        String insert = "insert into cliente(dni, nombres, direccion, estado) values(?,?,?,?)";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(insert);

            ps.setObject(1, c.getDni());
            ps.setObject(2, c.getNombre());
            ps.setObject(3, c.getDireccion());
            ps.setObject(4, c.getEstado());

            r = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error! " + this.getClass().getName() + " | " + ex.getMessage());
        }
        return r;
    }

}
