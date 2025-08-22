package com.dn.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
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


    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode( String code )
    {
        this.code = code;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher( String publisher )
    {
        this.publisher = publisher;
    }

    public int getYearRelease()
    {
        return yearRelease;
    }

    public void setYearRelease( int yearRelease )
    {
        this.yearRelease = yearRelease;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre( String genre )
    {
        this.genre = genre;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage( String language )
    {
        this.language = language;
    }

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
