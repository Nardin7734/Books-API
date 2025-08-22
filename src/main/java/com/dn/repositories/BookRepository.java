package com.dn.repositories;

import com.dn.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>
{
    public List<Optional<Book>> findByCode( String code );

    public List<Optional<Book>> findByAuthor(String author );

    public List<Optional<Book>> findByTitle( String title );

    public List<Optional<Book>> findByGenre( String genre );
}
