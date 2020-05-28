/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.vendedor;

import java.util.List;

/**
 *
 * @author Usuario
 */
public interface VendedorCRUD {

    public List listarVendedores();

    public int agregarVendedor(Vendedor v);

    public int actualizarVendedor(Vendedor v);

    public void eliminarVendedor(int id);

}
