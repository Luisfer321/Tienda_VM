
package com.tienda_vm.dao;

import com.tienda_vm.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDao 
extends JpaRepository<Producto,Long>
{
    
}
