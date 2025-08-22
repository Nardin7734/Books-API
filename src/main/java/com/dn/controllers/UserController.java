package com.dn.controllers;

import com.dn.data.dto.UserDTO;
import com.dn.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/user" )
@RestController
public class UserController
{
    @Autowired
    private UserServices userServices;

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public UserDTO findById( @PathVariable long id ) throws Exception
    {
        return userServices.findById( id );
    }

    @GetMapping( value = "/email", produces = MediaType.APPLICATION_JSON_VALUE )
    public UserDTO findByEmail( @RequestParam String email ) throws Exception
    {
        return userServices.findByEmail( email );
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public List<UserDTO> findAll() throws Exception
    {
        return userServices.findAll();
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public UserDTO create( @RequestBody @Valid UserDTO userDTO ) throws Exception
    {
        return userServices.create( userDTO );
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE )
    public UserDTO update( @RequestBody @Valid UserDTO userDto ) throws Exception
    {
        return userServices.update( userDto );
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete( @PathVariable long id ) throws Exception
    {
        userServices.delete( id );
        return ResponseEntity.noContent().build();
    }

}
