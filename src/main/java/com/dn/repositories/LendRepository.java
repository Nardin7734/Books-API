package com.dn.repositories;

import com.dn.models.Book;
import com.dn.models.Lend;
import com.dn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LendRepository extends JpaRepository<Lend, Long>
{
    public List<Lend> findByDtInBetween( LocalDate dtStart, LocalDate dtEnd );
    public List<Lend> findByDtOutBetween( LocalDate dtStart, LocalDate dtEnd );
    public List<Lend> findByBook( Book book );
    public List<Lend> findByUser( User user );
}
