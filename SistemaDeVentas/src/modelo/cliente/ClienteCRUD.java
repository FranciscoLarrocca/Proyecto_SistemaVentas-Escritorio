
package modelo.cliente;

import modelo.cliente.Cliente;
import java.util.List;

public interface ClienteCRUD {
    public List listar();
    public int agregarCliente(Cliente c);
    public int actualizarCliente(Cliente c);
    public void eliminarCliente(int id);
}
