package com.dn.repositories;

import com.dn.models.Book;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles( "test" )
public class BookRepositoryTest
{

    @Autowired
    BookRepository bookRepository;

    Book bookPersisted;
    Book bookNotPersisted;

    String code = "code";
    String title = "title";
    String genre = "genre";
    String author = "author";

    @BeforeEach
    public void setUp()
    {
        String publisher = "publisher";
        String language = "language";
        int yearRelease = 1999;

        bookPersisted = Book.builder().code(code).title(title)
                                    .genre(genre).author(author)
                                    .language(language).publisher(publisher)
                                    .yearRelease(yearRelease).build();
        bookRepository.save( bookPersisted );

        bookNotPersisted = Book.builder().code(code).title(title).id( 99 )
                                    .genre(genre).author(author)
                                    .language(language).publisher(publisher)
                                    .yearRelease(yearRelease).build();
    }

    @Test
    @DisplayName( "Should return a valid Book list" )
    void findByCodeContainingSuccess()
    {
        List<Book> books = bookRepository.findByCodeContaining( code );

        Assertions.assertFalse( books.isEmpty() );
        Assertions.assertEquals( 1, books.size() );
        Assertions.assertEquals( bookPersisted, books.getFirst() );
    }

    @Test
    @DisplayName( "Should return a null" )
    void findByCodeContainingFail()
    {
        List<Book> books = bookRepository.findByCodeContaining( "dd" );

        Assertions.assertTrue( books.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a valid Book" )
    void findByAuthorSuccess()
    {
        List<Book> books = bookRepository.findByAuthor( author );

        Assertions.assertFalse( books.isEmpty() );
        Assertions.assertEquals( 1, books.size() );
        Assertions.assertEquals( bookPersisted, books.getFirst() );
    }

    @Test
    @DisplayName( "Should return a null" )
    void findByAuthorFail()
    {
        List<Book> books = bookRepository.findByAuthor( "aut" );

        Assertions.assertTrue( books.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a valid Book" )
    void findByTitleContainingSuccess()
    {
        List<Book> books = bookRepository.findByTitleContaining( title );

        Assertions.assertFalse( books.isEmpty() );
        Assertions.assertEquals( 1, books.size() );
        Assertions.assertEquals( bookPersisted, books.getFirst() );
    }

    @Test
    @DisplayName( "Should return a null" )
    void findByTitleContainingFail()
    {
        List<Book> books = bookRepository.findByTitleContaining( "xx" );

        Assertions.assertTrue( books.isEmpty() );
    }

    @Test
    @DisplayName( "Should return a valid Book" )
    void findByGenreSuccess()
    {
        List<Book> books = bookRepository.findByGenre( genre );

        Assertions.assertFalse( books.isEmpty() );
        Assertions.assertEquals( 1, books.size() );
        Assertions.assertEquals( bookPersisted, books.getFirst() );
    }

    @Test
    @DisplayName( "Should return a null" )
    void findByGenreFail()
    {
        List<Book> books = bookRepository.findByGenre( "genrex" );

        Assertions.assertTrue( books.isEmpty() );
    }

}
