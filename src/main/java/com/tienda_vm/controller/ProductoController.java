
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
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        
        var lista=productoService.getProductos(false);
        model.addAttribute("productos", lista);     
          model.addAttribute("totalproductos", lista.size());
           var categorias=categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
    
    return "/producto/listado";
            
            
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
   @PostMapping("/guardar") 
    public String guardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()){
        productoService.save(producto);
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());

        firebaseStorageService.cargaImagen(imagenFile,
                "producto", producto.getIdProducto());
        producto.setRutaImagen(ruta);
        }
        return "redirect:/producto/listado";
    }
    
    @GetMapping("/modificar/{idProducto}")
    public String modificar(Producto producto, Model model){
        
    producto=productoService.getProducto(producto);
    model.addAttribute("producto", producto);
    var categorias=categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
    return "/producto/modifica";
    }
    
    @GetMapping("/eliminar/{idProducto}")
    public String elimina(Producto producto, Model model){
        
    productoService.delete(producto);
    
    return "redirect:/producto/listado";
    }
    
    
}
