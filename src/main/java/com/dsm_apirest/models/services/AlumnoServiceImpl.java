package com.dsm_apirest.models.services;

import com.dsm_apirest.models.dao.IAlumnoDao;
import com.dsm_apirest.models.entity.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl extends GenericServiceImpl<Alumno, IAlumnoDao> implements IAlumnoService{
    @Autowired
    public AlumnoServiceImpl(IAlumnoDao repository) {
        super(repository);
    }
}
