package com.dn.controllers;

import com.dn.data.dto.BookDTO;
import com.dn.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/book" )
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BookController
{
    @Autowired
    private BookServices bookServices;

    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public BookDTO findById( @PathVariable long id ) throws Exception
    {
        return bookServices.findById( id );
    }

    @GetMapping( value = "/code", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<BookDTO> findByCode( @RequestParam String code ) throws Exception
    {
        return bookServices.findByCode( code );
    }

    @GetMapping( value = "/title", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<BookDTO> findByTitle( @RequestParam String title ) throws Exception
    {
        return bookServices.findByTitle( title );
    }

    @GetMapping( value = "/author", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<BookDTO> findByAuthor( @RequestParam String author ) throws Exception
    {
        return bookServices.findByAuthor( author );
    }

    @GetMapping( value = "/genre", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<BookDTO> findByGenre( @RequestParam String genre ) throws Exception
    {
        return bookServices.findByGenre( genre );
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public List<BookDTO> findAll() throws Exception
    {
        return bookServices.findAll();
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    public BookDTO create( @RequestBody BookDTO bookDto ) throws Exception
    {
        return bookServices.create( bookDto );
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public BookDTO update( @RequestBody BookDTO bookDto ) throws Exception
    {
        return bookServices.update( bookDto );
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete( @PathVariable long id ) throws Exception
    {
        bookServices.delete( id );
        return ResponseEntity.noContent().build();
    }

}