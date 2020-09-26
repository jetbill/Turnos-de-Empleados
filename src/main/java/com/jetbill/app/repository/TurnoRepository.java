package com.jetbill.app.repository;


import com.jetbill.app.entity.Empleado;
import com.jetbill.app.entity.Turno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TurnoRepository extends CrudRepository<Turno, Long> {

    @Query("select e from Turno e where e.empleado.identificacion = :identificacion")
    Iterable<Turno> findAllByEmpleado(int identificacion);

    Iterable<Turno> findAllByHentradaBetween(Date hentrada, Date hsalida);

   @Query("select h from Turno h where h.hentrada= :hentrada and h.hsalida <= :hsalida")
   Iterable<Turno> findAllWithHentradaBefore(@Param("hentrada") Date hentrada,
                                             @Param("hsalida") Date hsalida);


    @Query("select t from Turno t where t.dia= :dia")
    Iterable<Turno> findByDia(String dia);

    @Query("select e from Empleado e where e.id= :id")
    public Empleado findEmpleadoById(Long id);

}
