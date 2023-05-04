package com.dsm_apirest.controllers;

import com.dsm_apirest.models.entity.User;
import com.dsm_apirest.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dsm_apirest.tools.PasswordEncrypter;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController extends GenericController<User, IUserService>{
    @Autowired
    public UserController(IUserService service) {
        super(service);
        this.nombreEntidad = "usuario";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        User newUser;
        Map<String, Object> response = new HashMap<>();


        try {
            user.setPassword(PasswordEncrypter.encrypt(user.getPassword()));
            newUser = service.save(user);
        } catch (DataAccessException ex){
            response.put("mensaje", String.format("Error al guardar %s en la base de datos", nombreEntidad));
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        response.put("mensaje", String.format("%s se ha guardado con éxito", nombreEntidad));
        response.put(nombreEntidad, newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        boolean loginResponse;
        HttpStatus httpStatus;

        try {
            Optional<User> oUser= service.findUserByUsername(user.getUsername());
            if (oUser.isEmpty()){
                throw new RuntimeException(String.format("El %s %s no existe", nombreEntidad, user.getUsername()));
            }

            String bdPassword = oUser.get().getPassword();
            String requestPassword = PasswordEncrypter.encrypt(user.getPassword());
            if (bdPassword.equals(requestPassword)) {
                loginResponse = true;
                httpStatus = HttpStatus.ACCEPTED;
            } else {
                loginResponse = false;
                httpStatus = HttpStatus.UNAUTHORIZED;
            }

        } catch (DataAccessException ex){
            response.put("mensaje", String.format("Error al guardar %s en la base de datos", nombreEntidad));
            response.put("error", Objects.requireNonNull(ex.getMessage()).concat(": ").concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(httpStatus).body(loginResponse);
    }
}
