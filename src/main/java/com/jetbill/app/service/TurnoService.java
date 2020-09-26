package com.jetbill.app.service;

import com.jetbill.app.entity.Empleado;
import com.jetbill.app.entity.Turno;

import java.util.Date;
import java.util.Optional;

public interface TurnoService {
    Iterable<Turno> findAll();
    Turno save(Turno turno);
    Turno edit(Turno turno);
    Optional<Turno> findById(Long id);
    Iterable<Turno>findbyEmpleado(int identificacion);
    Iterable<Turno> findByDay(String dia);
    Iterable<Turno> findBydateTime(Date hentrada, Date hsalida);
    //Empleado findEmpleadoById(Long id);


}
