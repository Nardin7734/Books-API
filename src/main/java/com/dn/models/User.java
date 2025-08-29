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
