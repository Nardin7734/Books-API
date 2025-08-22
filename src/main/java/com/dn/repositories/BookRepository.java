package com.interact.repositories;

import com.interact.models.Book;
import com.interact.models.User;
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
