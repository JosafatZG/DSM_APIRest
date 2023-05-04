package com.dsm_apirest.controllers;

import com.dsm_apirest.models.entity.Profesor;
import com.dsm_apirest.models.services.IProfesorService;
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
@RequestMapping("/profesores")
public class ProfesorController extends GenericController<Profesor, IProfesorService> {
    @Autowired
    public ProfesorController(IProfesorService service) {
        super(service);
        this.nombreEntidad = "profesor";
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Profesor profesor, BindingResult result){
        Optional<Profesor> oProfesor;
        Profesor profesorActualizar;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            return validar(result);
        }

        try{
            oProfesor = service.findById(id);
            if (oProfesor.isEmpty()){
                response.put("mensaje", String.format("%s con id %d no existe", nombreEntidad, id));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            profesorActualizar = oProfesor.get();
            profesorActualizar.setNombre(profesor.getNombre());
            profesorActualizar.setApellido(profesor.getApellido());
            profesorActualizar.setEdad(profesor.getEdad());

            profesorActualizar = service.save(profesorActualizar);
        } catch (DataAccessException ex){
            response.put("mensaje", String.format("Error al editar %s en la base de datos", nombreEntidad));
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", String.format("%s se ha actualizado con éxito", nombreEntidad));
        response.put(nombreEntidad, profesorActualizar);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/
}
