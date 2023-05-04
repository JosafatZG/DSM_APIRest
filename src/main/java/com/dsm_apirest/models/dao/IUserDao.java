package com.dsm_apirest.models.dao;

import com.dsm_apirest.models.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserDao extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
