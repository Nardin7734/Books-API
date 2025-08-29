package com.dn.services;

import com.dn.data.dto.BookDTO;
import com.dn.data.dto.LentDTO;
import com.dn.data.dto.UserDTO;
import com.dn.mappers.ObjectMapper;
import com.dn.models.Book;
import com.dn.models.Lend;
import com.dn.models.User;
import com.dn.repositories.LendRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class LendServices
{
    private Logger logger = Logger.getLogger( LendServices.class.getName() );

    @Autowired
    private LendRepository repository;

    @Autowired
    private ObjectMapper mapper;

    public List<LentDTO> findAll()
    {
        logger.info( "Finding all BooksBorrowed" );
        return mapper.parseList( repository.findAll(), LentDTO.class );
    }

    public List<LentDTO> findByDtInBetween(LocalDate start, LocalDate end ) throws Exception
    {
        logger.info( "Finding all BooksBorrowed between date start: " + start + " and " + end );
        return mapper.parseList( repository.findByDtInBetween( start, end ), LentDTO.class );
    }

    public List<LentDTO> findByDtOutBetween(LocalDate start, LocalDate end ) throws Exception
    {
        logger.info( "Finding all BooksBorrowed between date end: " + start + " and " + end );
        return mapper.parseList( repository.findByDtOutBetween( start, end ), LentDTO.class );
    }

    public List<LentDTO> findByBook(long bookId ) throws Exception
    {
        logger.info( "Finding all Loans for bookId: " + bookId );

        BookDTO bookDto = new BookDTO();
        bookDto.setId( bookId );
        Book book = mapper.parseObject( bookDto, Book.class );

        return mapper.parseList( repository.findByBook( book ), LentDTO.class );
    }

    public List<LentDTO> findByUser(long userId ) throws Exception
    {
        logger.info( "Finding all BooksBorrowed for userId: " + userId );

        UserDTO userDto = new UserDTO();
        userDto.setId( userId );
        User user = mapper.parseObject( userDto, User.class );

        return mapper.parseList( repository.findByUser( user ), LentDTO.class );
    }

    public LentDTO create(LentDTO bbDto ) throws Exception
    {
        logger.info( "Creating new BooksBorrowed" );
        var entity = repository.save( mapper.parseObject( bbDto, Lend.class ) );
        return mapper.parseObject( entity, LentDTO.class );
    }

    public LentDTO findById(long id ) throws Exception
    {
        var entity = repository.findById( id )
                .orElseThrow(() -> new BadRequestException( "No records found for this ID" ) );

        return mapper.parseObject( entity, LentDTO.class );
    }

    public LentDTO update(LentDTO bbDto ) throws Exception
    {
        var entity = repository.findById( bbDto.getId() )
                .orElseThrow( () -> new BadRequestException( "No records found for this BookBorrowed" ) );

        logger.info( "Atualizando BooksBorrowed" );
        entity.setBook( mapper.parseObject( bbDto.getBook(), Book.class ) );
        entity.setDtIn( bbDto.getDtIn());
        entity.setDtOut( bbDto.getDtOut() );
        entity.setUser( mapper.parseObject( bbDto.getUser(), User.class ) );

        return mapper.parseObject( repository.save( entity ), LentDTO.class );
    }

    public void delete( long id ) throws Exception
    {
        logger.info( "Deleting one BooksBorrowed" );
        Lend entity = repository.findById( id )
                .orElseThrow(() -> new BadRequestException( "No records found for this ID" ) );

        repository.delete( entity );
    }
}
