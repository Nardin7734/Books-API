package com.interact.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.interact.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO
{

    private static final long serialVersionUID = 1L;

    private long id;

    @Size( max = 100 )
    private String name;

    @Size( max = 200 )
    private String lastName;

    @Size( max = 13 )
    private String phoneNumber;

    @Size( min=8 )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email
    @Size( max = 200 )
    private String email;

    @Size( max = 50 )
    private String role;

    public UserDTO(){}
    public UserDTO( long id ){this.id = id;}

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        com.interact.models.User user = (com.interact.models.User) o;
        return getId() == user.getId() && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getEmail());
    }

}
