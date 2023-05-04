package com.dsm_apirest.models.services;

import com.dsm_apirest.models.entity.User;

import java.util.Optional;

public interface IUserService extends IGenericService<User>{
    Optional<User> findUserByUsername(String username);
}
