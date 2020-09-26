package com.jetbill.app.service;


import com.jetbill.app.entity.Empleado;
import com.jetbill.app.exeptions.EmpleadoExeption;
import com.jetbill.app.exeptions.TurnoExeption;
import com.jetbill.app.repository.EmpleadoRepository;
import com.jetbill.app.util.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    private EmpleadoRepository repository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Empleado save(Empleado empleado) {
        Optional<Empleado> emOpt = repository.findByIdentificacion(empleado.getIdentificacion());
        if (emOpt.isPresent()) {
            throw new EmpleadoExeption(Mensajes.ERROR_AL_GUARDAR +" la cedula "+
                    + empleado.getIdentificacion() + " ya existe");

        }

        return repository.save(empleado);
    }

    @Override
    public Empleado edit(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public Iterable<Empleado> finAll() {
        Iterable<Empleado> empleados = repository.findAll();
        if(!empleados.iterator().hasNext()){
            throw new EmpleadoExeption(Mensajes.NO_HAY_DATOS);
        }
        return empleados;
    }

    @Override
    public Optional<Empleado> findbyId(Long id) {
        Optional<Empleado> empOp = repository.findById(id);
        if(!empOp.isPresent()){
            throw new EmpleadoExeption("El ID "+id+Mensajes.ERROR_DE_CONSULTA);
        }

        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }


}



