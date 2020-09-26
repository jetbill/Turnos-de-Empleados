package com.jetbill.app.service;


import com.jetbill.app.entity.Empleado;

import java.util.Optional;

public interface EmpleadoService  {
    Empleado save(Empleado empleado);
    Iterable<Empleado> finAll();
    Optional<Empleado> findbyId(Long id);
    void deleteById(Long id);

}
