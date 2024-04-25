package com.tienda_vm.dao;

import com.tienda_vm.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta, Long> {

}
