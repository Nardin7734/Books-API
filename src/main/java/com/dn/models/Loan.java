package com.dn.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table( name = "loans" )
public class Loan implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @ManyToOne
    @JoinColumn( name = "user_id" )
    private User user;

    @ManyToOne
    @JoinColumn( name = "book_id" )
    private Book book;

    @Column( name = "dt_out", nullable = false )
    private LocalDate dtOut;

    @Column( name = "dt_in" )
    private LocalDate dtIn;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook( Book book ) {
        this.book = book;
    }

    public LocalDate getDtOut()
    {
        return dtOut;
    }

    public void setDtOut( LocalDate dtOut )
    {
        this.dtOut = dtOut;
    }

    public LocalDate getDtIn()
    {
        return dtIn;
    }

    public void setDtIn( LocalDate dtIn )
    {
        this.dtIn = dtIn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan that = (Loan) o;
        return getId() == that.getId() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getBook(), that.getBook()) && Objects.equals(getDtOut(), that.getDtOut()) && Objects.equals(getDtIn(), that.getDtIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getBook(), getDtOut(), getDtIn());
    }
}
