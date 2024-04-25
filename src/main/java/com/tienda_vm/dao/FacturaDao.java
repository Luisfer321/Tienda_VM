package com.tienda_vm.dao;


import com.tienda_vm.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository<Factura, Long> {

}
