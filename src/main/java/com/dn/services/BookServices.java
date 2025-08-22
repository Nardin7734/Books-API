package com.dn.services;

import com.dn.data.dto.BookDTO;
import com.dn.mappers.ObjectMapper;
import com.dn.models.Book;
import com.dn.repositories.BookRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServices
{
    private Logger logger = Logger.getLogger( BookServices.class.getName() );

    @Autowired
    BookRepository repository;

    @Autowired
    ObjectMapper mapper;

    public BookDTO findById( long id ) throws Exception
    {
        logger.info( "Finding one book by ID: " + id  );

        var entity = repository.findById( id )
                .orElseThrow(() -> new BadRequestException( "No records found for this ID" ) );

        return mapper.parseObject( entity, BookDTO.class );
    }

    public List<BookDTO> findByCode( String code ) throws Exception
    {
        logger.info( "Finding books by code: " + code );

        return mapper.parseList( repository.findByCode( code ), BookDTO.class );
    }

    public List<BookDTO> findByTitle( String title ) throws Exception
    {
        logger.info( "Finding books by title: " + title );

        return mapper.parseList( repository.findByTitle( title ), BookDTO.class);
    }

    public List<BookDTO> findByAuthor( String author ) throws Exception
    {
        logger.info( "Finding books by author: " + author );

        return mapper.parseList( repository.findByAuthor( author ), BookDTO.class );
    }

    public List<BookDTO> findByGenre( String genre ) throws Exception
    {
        logger.info( "Finding books by genre: " + genre );

        return mapper.parseList( repository.findByGenre( genre ), BookDTO.class );
    }

    public List<BookDTO> findAll() throws Exception
    {
        logger.info( "Finding all books" );

        return mapper.parseList( repository.findAll(), BookDTO.class );
    }

    public BookDTO create( BookDTO bookDto ) throws Exception
    {
        logger.info( "Creating a new book" );

        var entity = repository.save( mapper.parseObject( bookDto, Book.class ) );

        return mapper.parseObject( entity, BookDTO.class );
    }

    public BookDTO update( BookDTO bookDto ) throws Exception
    {
        Book entity = repository.findById( bookDto.getId() )
                .orElseThrow(() -> new BadRequestException( "No records found for this book" ) );

        logger.info( "Updating book..." );
        entity.setAuthor( bookDto.getAuthor() );
        entity.setCode( bookDto.getCode() );
        entity.setGenre( bookDto.getGenre() );
        entity.setLanguage( bookDto.getLanguage() );
        entity.setPublisher( bookDto.getPublisher() );
        entity.setTitle( bookDto.getTitle() );
        entity.setYearRelease( bookDto.getYearRelease() );


        return mapper.parseObject( repository.save( entity ), BookDTO.class );
    }

    public void delete( long id ) throws Exception
    {
        logger.info( "Deleting one book" );
        Book entity = repository.findById( id )
                .orElseThrow(() -> new BadRequestException( "No records found for this ID" ) );

        repository.delete( entity );
    }
}