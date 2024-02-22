
package com.tienda_vm.dao;

import com.tienda_vm.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDao 
extends JpaRepository<Categoria,Long>
{
    
}
