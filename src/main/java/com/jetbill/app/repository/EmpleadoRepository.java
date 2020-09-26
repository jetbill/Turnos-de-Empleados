package com.jetbill.app.repository;

import com.jetbill.app.entity.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado ,Long> {

    @Query("select e from Empleado e where e.identificacion= :identificacion")
    Optional<Empleado>findByIdentificacion(int identificacion);


}
