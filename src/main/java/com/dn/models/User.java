package com.dn.models;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "users" )
@Builder
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "name", nullable = false, length = 100 )
    private String name;

    @Column(name = "last_name", length = 200 )
    private String lastName;

    @Column(name = "phone_number", length = 13 )
    private String phoneNumber;

    @Column( name = "password", nullable = false, length = 1000 )
    private String password;

    @Column( name = "email", nullable = false, length = 200, unique = true )
    private String email;

    @Column( length = 50 )
    private String role;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber )
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getEmail());
    }
}
