package com.dn.data.dto;

import com.dn.models.Book;

import java.io.Serializable;
import java.util.Objects;

public class BookDTO implements Serializable
{

    private static final long serialVersionUID = 1L;

    private long id;
    private String code;
    private String title;
    private String author;
    private String publisher;
    private int yearRelease;
    private String genre;
    private String language;


    public BookDTO(){}
    public BookDTO( long id )
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
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
