package com.dn.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Entity
@Builder
@Table( name = "lend" )
public class Lend implements Serializable
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lend that = (Lend) o;
        return getId() == that.getId() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getBook(), that.getBook()) && Objects.equals(getDtOut(), that.getDtOut()) && Objects.equals(getDtIn(), that.getDtIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getBook(), getDtOut(), getDtIn());
    }
}
