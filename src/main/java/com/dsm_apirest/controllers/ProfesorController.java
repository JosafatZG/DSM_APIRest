package com.dsm_apirest.controllers;

import com.dsm_apirest.models.entity.Profesor;
import com.dsm_apirest.models.services.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/profesores")
public class ProfesorController extends GenericController<Profesor, IProfesorService> {
    @Autowired
    public ProfesorController(IProfesorService service) {
        super(service);
        this.nombreEntidad = "profesor";
    }
}
