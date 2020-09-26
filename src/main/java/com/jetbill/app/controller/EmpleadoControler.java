package com.jetbill.app.controller;
import com.jetbill.app.entity.Empleado;
import com.jetbill.app.service.EmpleadoService;
import com.jetbill.app.util.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/empleado")
public class EmpleadoControler {

    private EmpleadoService service;

    @Autowired
    public EmpleadoControler(EmpleadoService service) {this.service=service;}



    @GetMapping
    public ResponseEntity<?> findAll(){
        Map<String,Object> response = new HashMap<>();
        Iterable<Empleado> empleados = null;
        try {
            empleados = service.finAll();
        }catch (DataAccessException e){
            response.put("mensaje", Mensajes.CONEXION_ERROR);
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleados,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Empleado empleado, BindingResult result){
        Empleado empleadodb = null;
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()) {
            return ValidateFields.validator(result);
        }
        try {
            empleadodb = service.save(empleado);
        }catch (DataAccessException e){
            response.put("mensaje", Mensajes.CONEXION_ERROR);
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

        }

        response.put("mensaje", Mensajes.REGISTRO_EXITOSO);
        response.put("empleado",empleadodb);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Empleado empleadoNew,@PathVariable Long id ){
        Optional<Empleado> empleadoBuscado = service.findbyId(id);
        if(empleadoBuscado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Empleado empleadoEditado = empleadoBuscado.get();
        empleadoEditado.setNombre(empleadoNew.getNombre());
        empleadoEditado.setApellido(empleadoNew.getApellido());
        empleadoEditado.setIdentificacion(empleadoNew.getIdentificacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(empleadoEditado));
    }
    
    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Empleado> empleado = null;
        try {
            empleado  = service.findbyId(id);
        }catch (DataAccessException e){
            response.put("mensaje", Mensajes.CONEXION_ERROR);
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empleado,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            service.deleteById(id);

        }catch (DataAccessException e){
            response.put("mensaje","Error no se pudo eliminar el empleado ".concat(id.toString()));
            response.put("error ", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","empleado "+id+" eliminado con exito!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }


}
