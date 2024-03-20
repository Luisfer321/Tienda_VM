
package com.tienda_vm.dao;
import java.util.List;
import com.tienda_vm.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductoDao 
extends JpaRepository<Producto,Long>
{
    //se define un metodo con una consulta JPA primera forma
    
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
     //se define un metodo con una consulta JPQL PARA HACER CONSULTAS A LA BASE DE BATOS, segunda forma
    @Query(value="SELECT p "
            + "FROM Producto p "
            + "WHERE p.precio "
            + "BETWEEN :precioinf and :preciosup "
            + "ORDER BY p.descripcion ASC")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
     //se define un metodo con una consulta SQL PARA HACER CONSULTAS A LA BASE DE BATOS, tercera forma 
    @Query(nativeQuery=true,
            value="SELECT * "
            + "FROM producto p "
            + "WHERE p.precio "
            + "BETWEEN :precioinf and :preciosup "
            + "ORDER BY p.descripcion ASC")
    public List<Producto> consultaSQL(double precioInf, double precioSup);
    
}
//en la tarea 4 tenemos que crea una consulta de lo que querramos sobre 