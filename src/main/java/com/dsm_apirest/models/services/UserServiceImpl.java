package com.dsm_apirest.models.services;

import com.dsm_apirest.models.dao.IUserDao;
import com.dsm_apirest.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class UserServiceImpl extends GenericServiceImpl<User, IUserDao> implements IUserService{
    @Autowired
    public UserServiceImpl(IUserDao repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }
}
