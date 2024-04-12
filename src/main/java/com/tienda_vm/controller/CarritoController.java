
package com.tienda_vm.controller;

import com.tienda_vm.domain.Item;
import com.tienda_vm.domain.Producto;
import com.tienda_vm.service.ItemService;
import com.tienda_vm.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class CarritoController {
    
    
   @Autowired
   private ItemService itemService;
   @Autowired
   private ProductoService productoService;
   
    
    
    
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model,Item item) {
        
      Item item2 = itemService.get(item);
        
        if (item2==null) {
            
          Producto p = productoService.getProducto(item);
          
          item2=new Item(p);
          
        }
       
        itemService.save(item2);
        
       var lista=itemService.gets();
       var totalCarrito=0;
       var carritoTotalVenta=0;
       
       for (Item i : lista) {
           totalCarrito+=i.getCantidad();
           carritoTotalVenta+=(i.getCantidad()*i.getPrecio());
       }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
     
    @GetMapping("/carrito/listado")
    public String listado(Model model) {
        
        var items = itemService.gets();
        model.addAttribute("items", items);
        
        
        var carritoTotalVenta=0;
                
        for (Item i : items) {
           carritoTotalVenta+=(i.getCantidad()*i.getPrecio());
       }
        
        model.addAttribute("carritoTotal", carritoTotalVenta);
        
      return "/carrito/listado";
    
}
}
