
package com.tienda_vm.controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.tienda_vm.domain.Categoria;
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
    
    @GetMapping("/listado/{idCategoria}")
    public String listadoCategoria(Categoria categoria, Model model){
    categoria=categoriaService.getCategoria(categoria);
    var lista=categoria.getProducto();
   
     model.addAttribute("productos", lista);     
          
          
           var categorias=categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
    
    return "/pruebas/listado";
    }
    
   @GetMapping("/listado2")
    public String listado2(Model model) {
        
        var lista=productoService.getProductos(false);
        model.addAttribute("productos", lista);     
           
    
    return "/pruebas/listado2";      
    }  
    
   @PostMapping("/query1")
   public String consultaJPA(
   @RequestParam(value="precioInf") double precioInf,
   @RequestParam(value="precioSup") double precioSup, 
   Model model){
   var productos=productoService.consultaJPA(precioInf, precioSup);
    model.addAttribute("productos", productos);
    model.addAttribute("precioInf", precioInf);
    model.addAttribute("precioSup", precioSup);
    return "/pruebas/listado2";
   }
   
    @PostMapping("/query2")
   public String consultaJPQL(
   @RequestParam(value="precioInf") double precioInf,
   @RequestParam(value="precioSup") double precioSup, 
   Model model){
   var productos=productoService.consultaJPQL(precioInf, precioSup);
    model.addAttribute("productos", productos);
    model.addAttribute("precioInf", precioInf);
    model.addAttribute("precioSup", precioSup);
    return "/pruebas/listado2";
   }
   
   @PostMapping("/query3")
   public String consultaSQL(
   @RequestParam(value="precioInf") double precioInf,
   @RequestParam(value="precioSup") double precioSup, 
   Model model){
   var productos=productoService.consultaSQL(precioInf, precioSup);
    model.addAttribute("productos", productos);
    model.addAttribute("precioInf", precioInf);
    model.addAttribute("precioSup", precioSup);
    return "/pruebas/listado2";
   }
}
