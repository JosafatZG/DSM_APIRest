package com.dsm_apirest.controllers;

import com.dsm_apirest.models.services.IGenericService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public class GenericController<E, S extends IGenericService<E>> {
    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<E> e = (List<E>) service.findAll();
        if(e.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id", required = false) Long id){
        Optional<E> e;
        Map<String, Object> response = new HashMap<>();

        try {
            e = service.findById(id);
            if (e.isEmpty()){
                response.put("mensaje", String.format("%s con id %d no existe", nombreEntidad, id));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException ex){
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(e.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody E e, BindingResult result){
        E eNew;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            return validar(result);
        }

        try {
            eNew = service.save(e);
        } catch (DataAccessException ex){
            response.put("mensaje", String.format("Error al guardar %s en la base de datos", nombreEntidad));
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", String.format("%s se ha guardado con éxito", nombreEntidad));
        response.put(nombreEntidad, eNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody E e, BindingResult result){
        E eUpdate;
        Map<String, Object> response = new HashMap<>();

        Optional<E> oe = service.findById(id);
        if (oe.isEmpty()){
            throw new RuntimeException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }

        if (result.hasErrors()){
            return validar(result);
        }

        try {
            eUpdate = e;
            eUpdate = service.save(eUpdate);
        } catch (DataAccessException ex){
            response.put("mensaje", String.format("Error al guardar %s en la base de datos", nombreEntidad));
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", String.format("%s se ha actualizado con éxito", nombreEntidad));
        response.put(nombreEntidad, eUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        try{
            service.deleteById(id);
        } catch (DataAccessException ex) {
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", String.format("%s con id %d se ha eliminado con éxito!", nombreEntidad, id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

    }

    protected ResponseEntity<Map<String, List<String>>> validar(BindingResult result) {
        Map<String, List<String>> errores = new HashMap<>();
        List<String> errors = result.getFieldErrors()
                .stream()
                .map(fieldError -> "El campo' " + fieldError.getField() + "' " + fieldError.getDefaultMessage())
                .toList();
        errores.put("errors", errors);
        return ResponseEntity.badRequest().body(errores);
    }
}
