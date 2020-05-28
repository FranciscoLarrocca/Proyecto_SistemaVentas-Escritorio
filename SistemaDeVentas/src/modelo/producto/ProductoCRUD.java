package modelo.producto;

import java.util.List;

public interface ProductoCRUD {

    public List listarProductos();

    public int agregarProducto(Producto p);

    public int actualizarProducto(Producto p);

    public void eliminarProducto(int i);
}
