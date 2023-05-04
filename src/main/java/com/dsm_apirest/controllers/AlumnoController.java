package com.dsm_apirest.controllers;

import com.dsm_apirest.models.entity.Alumno;
import com.dsm_apirest.models.services.IAlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends GenericController<Alumno, IAlumnoService>{

    @Autowired
    public AlumnoController(IAlumnoService service) {
        super(service);
        this.nombreEntidad = "alumno";
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Alumno alumno, BindingResult result){
        Optional<Alumno> oAlumno;
        Alumno alumnoActualizar;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            return validar(result);
        }

        try{
            oAlumno = service.findById(id);
            if (oAlumno.isEmpty()){
                response.put("mensaje", String.format("%s con id %d no existe", nombreEntidad, id));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            alumnoActualizar = oAlumno.get();
            alumnoActualizar.setNombre(alumno.getNombre());
            alumnoActualizar.setApellido(alumno.getApellido());
            alumnoActualizar.setEdad(alumno.getEdad());

            alumnoActualizar = service.save(alumnoActualizar);
        } catch (DataAccessException ex){
            response.put("mensaje", String.format("Error al editar %s en la base de datos", nombreEntidad));
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", String.format("%s se ha actualizado con éxito", nombreEntidad));
        response.put(nombreEntidad, alumnoActualizar);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/
}
