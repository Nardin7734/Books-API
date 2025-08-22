package com.interact.services;

import com.interact.data.dto.BookDTO;
import com.interact.data.dto.LoanDTO;
import com.interact.data.dto.UserDTO;
import com.interact.mappers.ObjectMapper;
import com.interact.models.Book;
import com.interact.models.Loan;
import com.interact.models.User;
import com.interact.repositories.LoanRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class LoanServices
{
    private Logger logger = Logger.getLogger( LoanServices.class.getName() );

    @Autowired
    private LoanRepository repository;

    @Autowired
    private ObjectMapper mapper;

    public List<LoanDTO> findAll()
    {
        logger.info( "Finding all BooksBorrowed" );
        return mapper.parseList( repository.findAll(), LoanDTO.class );
    }

    public List<LoanDTO> findByDtInBetween(LocalDate start, LocalDate end ) throws Exception
    {
        logger.info( "Finding all BooksBorrowed between date start: " + start + " and " + end );
        return mapper.parseList( repository.findByDtInBetween( start, end ), LoanDTO.class );
    }

    public List<LoanDTO> findByDtOutBetween(LocalDate start, LocalDate end ) throws Exception
    {
        logger.info( "Finding all BooksBorrowed between date end: " + start + " and " + end );
        return mapper.parseList( repository.findByDtOutBetween( start, end ), LoanDTO.class );
    }

    public List<LoanDTO> findByBook( long bookId ) throws Exception
    {
        logger.info( "Finding all Loans for bookId: " + bookId );

        BookDTO bookDto = new BookDTO();
        bookDto.setId( bookId );
        Book book = mapper.parseObject( bookDto, Book.class );

        return mapper.parseList( repository.findByBook( book ), LoanDTO.class );
    }

    public List<LoanDTO> findByUser(long userId ) throws Exception
    {
        logger.info( "Finding all BooksBorrowed for userId: " + userId );

        UserDTO userDto = new UserDTO();
        userDto.setId( userId );
        User user = mapper.parseObject( userDto, User.class );

        return mapper.parseList( repository.findByUser( user ), LoanDTO.class );
    }

    public LoanDTO create( LoanDTO bbDto ) throws Exception
    {
        logger.info( "Creating new BooksBorrowed" );
        var entity = repository.save( mapper.parseObject( bbDto, Loan.class ) );
        return mapper.parseObject( entity, LoanDTO.class );
    }

    public LoanDTO findById(long id ) throws Exception
    {
        var entity = repository.findById( id )
                .orElseThrow(() -> new BadRequestException( "No records found for this ID" ) );

        return mapper.parseObject( entity, LoanDTO.class );
    }

    public LoanDTO update(LoanDTO bbDto ) throws Exception
    {
        var entity = repository.findById( bbDto.getId() )
                .orElseThrow( () -> new BadRequestException( "No records found for this BookBorrowed" ) );

        logger.info( "Atualizando BooksBorrowed" );
        entity.setBook( mapper.parseObject( bbDto.getBook(), Book.class ) );
        entity.setDtIn( bbDto.getDtIn());
        entity.setDtOut( bbDto.getDtOut() );
        entity.setUser( mapper.parseObject( bbDto.getUser(), User.class ) );

        return mapper.parseObject( repository.save( entity ), LoanDTO.class );
    }

    public void delete( long id ) throws Exception
    {
        logger.info( "Deleting one BooksBorrowed" );
        Loan entity = repository.findById( id )
                .orElseThrow(() -> new BadRequestException( "No records found for this ID" ) );

        repository.delete( entity );
    }
}
