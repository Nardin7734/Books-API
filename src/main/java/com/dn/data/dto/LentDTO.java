package com.dn.data.dto;

import java.time.LocalDate;

public class LentDTO
{

    private long id;
    private UserDTO user;
    private BookDTO book;
    private LocalDate dtOut;
    private LocalDate dtIn;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public UserDTO getUser()
    {
        return user;
    }

    public void setUser( UserDTO user )
    {
        this.user = user;
    }

    public BookDTO getBook()
    {
        return book;
    }

    public void setBook( BookDTO book ) {
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

}
