package com.jetbill.app.service;

import com.jetbill.app.entity.Empleado;
import com.jetbill.app.entity.Turno;
import com.jetbill.app.exeptions.EmpleadoExeption;
import com.jetbill.app.exeptions.TurnoExeption;
import com.jetbill.app.repository.EmpleadoRepository;
import com.jetbill.app.repository.TurnoRepository;
import com.jetbill.app.util.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {
    private TurnoRepository repository;
    private EmpleadoRepository emrepo;

    @Autowired
    public TurnoServiceImpl(TurnoRepository repository,EmpleadoRepository emrepo) {
        this.repository = repository;
        this.emrepo = emrepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Turno> findAll() {
        Iterable<Turno> turnos = repository.findAll();
        if (!turnos.iterator().hasNext()) {
            throw new TurnoExeption(Mensajes.NO_HAY_DATOS);
        }
        return turnos;
    }

    @Override
    @Transactional
    public Turno save(Turno turno) {

        Turno trn = null;
        if(turno.getHentrada() != null && turno.getHsalida() != null) {

            trn = repository.save(turno);
        }
        return trn;
    }

    @Override
    @Transactional
    public Optional<Turno> findById(Long id) {
        Optional<Turno> turOp = repository.findById(id);
        if (!turOp.isPresent()) {
            throw new TurnoExeption("El turno" + id + Mensajes.ERROR_DE_CONSULTA);
        }

        return repository.findById(id);
    }


    @Override
    @Transactional
    public Iterable<Turno> findbyEmpleado(int identificacion) {
        Iterable<Turno> turnos = repository.findAllByEmpleado(identificacion);
        if (!turnos.iterator().hasNext()) {
            throw new TurnoExeption(Mensajes.ERROR_DE_CONSULTA + " la cedula " +
                    +identificacion + " no existe");

        }
        return turnos;
    }

    @Override
    @Transactional
    public Iterable<Turno> findByDay(String dia) {
        return repository.findByDia(dia);
    }

    @Override
    @Transactional
    public Iterable<Turno> findBydateTime(Date hentrada, Date hsalida) {
        return repository.findAllWithHentradaBefore(hentrada, hsalida);
    }

    /*@Override
    public Empleado findEmpleadoById(Long id) {
        return repository.findEmpleadoById(id);
    }*/
}
