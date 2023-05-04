package com.dsm_apirest.models.dao;

import com.dsm_apirest.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface IAlumnoDao extends CrudRepository<Alumno, Long> {
}
