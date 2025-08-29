package com.dn.controllers;

import com.dn.data.dto.LentDTO;
import com.dn.services.LendServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/loan" )
@RestController
public class LoanController
{
    @Autowired
    private LendServices lendServices;

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public LentDTO findById(@PathVariable long id ) throws Exception
    {
        return lendServices.findById( id );
    }

    @GetMapping( value = "/book-id/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LentDTO> findByBookId(@PathVariable long bookId ) throws Exception
    {
        return lendServices.findByBook( bookId );
    }

    @GetMapping( value = "/user-id", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LentDTO> findByUserId(@RequestParam long id ) throws Exception
    {
        return lendServices.findByUser( id );
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LentDTO> findAll() throws Exception
    {
        return lendServices.findAll();
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public LentDTO create(@RequestBody LentDTO lentDto) throws Exception
    {
        return lendServices.create(lentDto);
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public LentDTO update(@RequestBody LentDTO lentDto) throws Exception
    {
        return lendServices.update(lentDto);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id ) throws Exception
    {
        lendServices.delete( id );
        return ResponseEntity.noContent().build();
    }
}
