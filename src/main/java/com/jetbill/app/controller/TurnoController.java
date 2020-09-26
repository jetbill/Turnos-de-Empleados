package com.jetbill.app.controller;

import com.jetbill.app.entity.Empleado;
import com.jetbill.app.entity.Turno;
import com.jetbill.app.repository.EmpleadoRepository;
import com.jetbill.app.service.TurnoService;
import com.jetbill.app.util.Mensajes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
    private TurnoService service;
    private EmpleadoRepository empService;
    private Logger logger = LoggerFactory.getLogger(TurnoController.class);

    @Autowired
    public TurnoController(TurnoService service,EmpleadoRepository empService) {
        this.service = service;
        this.empService = empService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        Iterable<Turno> turnos = null;
        Map<String, Object> response = new HashMap<>();
        try {
            turnos = service.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", Mensajes.CONEXION_ERROR);
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("turnos", turnos);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "cedula/{identificacion}")
    public ResponseEntity<?> buscarTurnoDelEmpleado(@PathVariable int identificacion) {
        Map<String, Object> response = new HashMap<>();
        Iterable<Turno> turnos = service.findbyEmpleado(identificacion);
        response.put("turnos", turnos);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Turno turno, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Turno turnodb = null;
        if(result.hasErrors()) {
            return ValidateFields.validator(result);
        }
        try {

            turnodb = service.save(turno);

        }catch (DataAccessException e){
            response.put("mensaje", Mensajes.CONEXION_ERROR);
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }
        response.put("mensaje", Mensajes.REGISTRO_EXITOSO);
        response.put("turno",turnodb);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @GetMapping("/entradaysalida")
    public ResponseEntity<?> findBydateTime(
            @RequestParam("hentrada") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date hentrada,
            @RequestParam("hsalida") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date hsalida) {
        Iterable<Turno> horas = null;
        Map<String, Object> response = new HashMap<>();
        try {
            horas = service.findBydateTime(hentrada,hsalida);
        }catch (DataAccessException e){
            response.put("mensaje", Mensajes.CONEXION_ERROR);
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }
        response.put("mensaje",Mensajes.CONSULTA_EXITOSA);
        response.put("horarios",horas);

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @GetMapping("/dias/{dia}")
    public Iterable<Turno> findByday(@PathVariable String dia) {
        return service.findByDay(dia);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> edit(@RequestBody Turno turnoNew,@PathVariable Long id) {
        Optional<Turno> turnobuscado = service.findById(id);
        if(turnobuscado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Turno turnoEditado = turnobuscado.get();
        turnoEditado.setDia(turnoNew.getDia());
        turnoEditado.setHentrada(turnoNew.getHentrada());
        turnoEditado.setHsalida(turnoNew.getHsalida());
        turnoEditado.setEmpleado(turnoNew.getEmpleado());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(turnoEditado));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.findById(id);
    }


}
