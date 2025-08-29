package com.dn.repositories;

import com.dn.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>
{
    public List<Book> findByCodeContaining( String code );

    public List<Book> findByAuthor( String author );

    public List<Book> findByTitleContaining(String title );

    public List<Book> findByGenre( String genre );
}
