package com.tienda_vm.service.impl;

import com.tienda_vm.dao.ProductoDao;
import com.tienda_vm.domain.Producto;
import com.tienda_vm.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl1 implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {

        var lista = productoDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    //se define un metodo con una consulta JPA primera forma
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaJPA(double precioInf, double precioSup){
        
    return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    
    }

    //se define un metodo con una consulta JPQL PARA HACER CONSULTAS A LA BASE DE BATOS, segunda forma
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaJPQL(double precioInf, double precioSup){
    
      return productoDao.consultaJPQL(precioInf, precioSup); 
    }

    //se define un metodo con una consulta SQL PARA HACER CONSULTAS A LA BASE DE BATOS, tercera forma 
    @Override
    @Transactional(readOnly = true)
    public List<Producto> consultaSQL(double precioInf, double precioSup){
    
        return productoDao.consultaSQL(precioInf, precioSup);
    }

}
