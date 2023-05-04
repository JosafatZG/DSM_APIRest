package com.dsm_apirest.models.services;

import com.dsm_apirest.models.dao.IProfesorDao;
import com.dsm_apirest.models.entity.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl extends GenericServiceImpl<Profesor, IProfesorDao> implements IProfesorService{

    @Autowired
    public ProfesorServiceImpl(IProfesorDao repository) {
        super(repository);
    }
}
