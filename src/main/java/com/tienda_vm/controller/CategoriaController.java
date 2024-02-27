
package com.tienda_vm.controller;

import com.tienda_vm.domain.Categoria;
import com.tienda_vm.service.CategoriaService;
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
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        
        var lista=categoriaService.getCategorias(false);
        model.addAttribute("categorias", lista);
          model.addAttribute("totalcategorias", lista.size());
    
    return "/categoria/listado";
            
            
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
   @PostMapping("/guardar") 
    public String guardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()){
        categoriaService.save(categoria);
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());

        firebaseStorageService.cargaImagen(imagenFile,
                "categoria", categoria.getIdCategoria());
        categoria.setRutaImagen(ruta);
        }
        return "redirect:/categoria/listado";
    }
    
    @GetMapping("/modificar/{idCategoria}")
    public String modificar(Categoria categoria, Model model){
        
    categoria=categoriaService.getCategoria(categoria);
    model.addAttribute("categoria", categoria);
    return "/categoria/modifica";
    }
    
    @GetMapping("/eliminar/{idCategoria}")
    public String elimina(Categoria categoria, Model model){
        
    categoriaService.delete(categoria);
    
    return "redirect:/categoria/listado";
    }
    
    
}
