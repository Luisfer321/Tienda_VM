
package com.tienda_vm.service;

import com.tienda_vm.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    
public List<Categoria> getCategorias(boolean activos);    
}
