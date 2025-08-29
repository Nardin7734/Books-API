package com.dn.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Builder
@Table( name = "books" )
public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @Column( name = "code", nullable = false, length = 50 )
    private String code;

    @Column( name = "title", nullable = false, length = 200 )
    private String title;

    @Column( name = "author", nullable = false, length = 200 )
    private String author;

    @Column( name = "publisher", nullable = false, length = 200 )
    private String publisher;

    @Column( name = "year_release", nullable = false )
    private int yearRelease;

    @Column( name = "genre", nullable = false, length = 100 )
    private String genre;

    @Column( name = "language", nullable = false, length = 100 )
    private String language;


    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getId() == book.getId() && Objects.equals(getCode(), book.getCode()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getCode(), getTitle(), getAuthor());
    }
}
