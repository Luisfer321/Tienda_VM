
package com.tienda_vm.dao;

import com.tienda_vm.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolDao 
extends JpaRepository<Rol,Long>
{
    
}
