package com.dn.repositories;

import com.dn.models.Book;
import com.dn.models.Loan;
import com.dn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long>
{
    public List<Optional<Loan>> findByDtInBetween(LocalDate dtStart, LocalDate dtEnd );
    public List<Optional<Loan>> findByDtOutBetween(LocalDate dtStart, LocalDate dtEnd );
    public List<Optional<Loan>> findByBook(Book book );
    public List<Optional<Loan>> findByUser(User user );
}
