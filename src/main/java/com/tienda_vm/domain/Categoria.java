
package com.tienda_vm.domain;
import java.util.List
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@table(name="categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @column(name="id_categoria")
    private Long idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
    
     @OneToMany
    @JoinColum(name="id?categoria")
    private List<producto> producto;
}
/*/*

create table techshop.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria))

/*
