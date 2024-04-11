
package com.tienda_vm.dao;

import com.tienda_vm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao 
extends JpaRepository<Usuario,Long>
{
    
   public Usuario findByUsername(String username); 
    
    
}
