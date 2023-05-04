package com.dsm_apirest.controllers;

import com.dsm_apirest.models.entity.Alumno;
import com.dsm_apirest.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends GenericController<Alumno, IAlumnoService>{

    @Autowired
    public AlumnoController(IAlumnoService service) {
        super(service);
        this.nombreEntidad = "alumno";
    }
}
