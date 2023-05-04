package com.dsm_apirest.models.dao;

import com.dsm_apirest.models.entity.Profesor;
import org.springframework.data.repository.CrudRepository;

public interface IProfesorDao extends CrudRepository<Profesor, Long> {
}
