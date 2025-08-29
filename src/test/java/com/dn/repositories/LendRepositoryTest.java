package com.dn.repositories;

import com.dn.models.Book;
import com.dn.models.Lend;
import com.dn.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@DataJpaTest
@ActiveProfiles( "test" )
class LendRepositoryTest
{
    @Autowired
    LendRepository lendRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    Book bookPersisted;
    Book bookNotPersisted;

    User userPersisted;
    User userNotPersisted;

    Lend lend = null;


    @BeforeEach
    public void setUp()
    {
        String email = "teste@gmail.com";
        String name = "TesteName";
        String password = "TestePassword";

        String code = "code";
        String title = "title";
        String genre = "genre";
        String author = "author";
        String publisher = "publisher";
        String language = "language";
        int yearRelease = 1999;

        LocalDate dtOut = LocalDate.of( 2025, Month.JANUARY, 15 );
        LocalDate dtIn = LocalDate.of( 2025, Month.MARCH, 15 );


        userPersisted = User.builder()
                            .name( name )
                            .email( email )
                            .password( password ).build();

        userRepository.save( userPersisted );

        userNotPersisted = User.builder()
                            .id( 99 )
                            .name( name )
                            .email( email )
                            .password( password ).build();

        bookPersisted = Book.builder()
                        .code(code).title(title)
                        .genre(genre).author(author)
                        .publisher(publisher).language(language)
                        .yearRelease(yearRelease).build();

        bookRepository.save( bookPersisted );

        bookNotPersisted = Book.builder().id( 99 )
                        .code(code).title(title)
                        .genre(genre).author(author)
                        .publisher(publisher).language(language)
                        .yearRelease(yearRelease).build();

        lend = Lend.builder().book( bookPersisted ).dtOut( dtOut )
                                .dtIn( dtIn ).user( userPersisted ).build();

        lendRepository.save( lend );
    }

    @Test
    @DisplayName( "Should return a valid Lend list" )
    void findByDtInBetweenSuccess()
    {
        LocalDate start = LocalDate.of( 2025, Month.MARCH, 1 );
        LocalDate end = LocalDate.of( 2025, Month.MARCH, 31 );

        List<Lend> lends = lendRepository.findByDtInBetween( start, end );

        Assertions.assertFalse( lends.isEmpty() );
        Assertions.assertEquals( lends.getFirst(), lend );
    }

    @Test
    @DisplayName( "Should return a empty Lend list" )
    void findByDtInBetweenFail()
    {
        LocalDate start = LocalDate.of( 2025, Month.MARCH, 16 );
        LocalDate end = LocalDate.of( 2025, Month.MARCH, 31 );

        List<Lend> lends = lendRepository.findByDtInBetween( start, end );

        Assertions.assertTrue( lends.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a valid Lend object" )
    void findByDtOutBetweenSuccess()
    {
        LocalDate start = LocalDate.of( 2025, Month.JANUARY, 1 );
        LocalDate end = LocalDate.of( 2025, Month.JANUARY, 31 );

        List<Lend> lends = lendRepository.findByDtOutBetween( start, end );

        Assertions.assertFalse( lends.isEmpty() );
        Assertions.assertEquals( lends.getFirst(), lend );
    }

    @Test
    @DisplayName( "Should return a null Lend object" )
    void findByDtOutBetweenFail()
    {
        LocalDate start = LocalDate.of( 2025, Month.JANUARY, 16 );
        LocalDate end = LocalDate.of( 2025, Month.JANUARY, 31 );

        List<Lend> lends = lendRepository.findByDtOutBetween( start, end );

        Assertions.assertTrue( lends.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a valid Lend object" )
    void findByBookSuccess()
    {
        List<Lend> lends = lendRepository.findByBook( bookPersisted );

        Assertions.assertNotNull( lends );
        Assertions.assertFalse( lends.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a null Lend object" )
    void findByBookFail()
    {
        List<Lend> lends = lendRepository.findByBook( bookNotPersisted );

        Assertions.assertTrue( lends.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a valid Lend object" )
    void findByUserSuccess()
    {
        List<Lend> lends = lendRepository.findByUser( userPersisted );

        Assertions.assertNotNull( lends );
        Assertions.assertFalse( lends.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a null Lend object" )
    void findByUserFail()
    {
        List<Lend> lends = lendRepository.findByUser( userNotPersisted );

        Assertions.assertTrue( lends.isEmpty() );
    }
}
