
package com.tienda_vm.controller;

import com.tienda_vm.domain.Producto;
import com.tienda_vm.service.CategoriaService;
import com.tienda_vm.service.ProductoService;
import com.tienda_vm.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        
        var lista=productoService.getProductos(false);
        model.addAttribute("productos", lista);     
          
          
           var categorias=categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
    
    return "/pruebas/listado";
            
            
    }
    
}
