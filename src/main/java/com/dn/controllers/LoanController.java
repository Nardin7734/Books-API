package com.interact.controllers;

import com.interact.data.dto.LoanDTO;
import com.interact.services.LoanServices;
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
    private LoanServices loanServices;

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public LoanDTO findById(@PathVariable long id ) throws Exception
    {
        return loanServices.findById( id );
    }

    @GetMapping( value = "/book-id/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LoanDTO> findByBookId( @PathVariable long bookId ) throws Exception
    {
        return loanServices.findByBook( bookId );
    }

    @GetMapping( value = "/user-id", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LoanDTO> findByUserId( @RequestParam long id ) throws Exception
    {
        return loanServices.findByUser( id );
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public List<LoanDTO> findAll() throws Exception
    {
        return loanServices.findAll();
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public LoanDTO create( @RequestBody LoanDTO loanDto ) throws Exception
    {
        return loanServices.create( loanDto );
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public LoanDTO update( @RequestBody LoanDTO loanDto ) throws Exception
    {
        return loanServices.update( loanDto );
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id ) throws Exception
    {
        loanServices.delete( id );
        return ResponseEntity.noContent().build();
    }
}
