
package com.tienda_vm.service;

import com.tienda_vm.domain.Producto;
import java.util.List;


public interface ProductoService {
    
public List<Producto> getProductos(boolean activos); 

public Producto getProducto(Producto producto);

public void save(Producto producto);

public void delete(Producto producto);

  //se define un metodo con una consulta JPA primera forma
    
    public List<Producto> consultaJPA(double precioInf, double precioSup);
    
     //se define un metodo con una consulta JPQL PARA HACER CONSULTAS A LA BASE DE BATOS, segunda forma
   
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
     //se define un metodo con una consulta SQL PARA HACER CONSULTAS A LA BASE DE BATOS, tercera forma 
    
    public List<Producto> consultaSQL(double precioInf, double precioSup);
}
