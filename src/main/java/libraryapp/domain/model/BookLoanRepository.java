package libraryapp.domain.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface BookLoanRepository extends Repository<BookLoan, Long> {

    @Query("FROM BookLoan bl WHERE bl.account = :account")
    Collection<BookLoan> findManyBookLoanByMember(@Param("account")MemberAccount account);

    @Query("FROM BookLoan bl WHERE bl.bookCopy = :bookCopy")
    BookLoan findOneBookLoanByBook(@Param("bookCopy")BookCopy bookCopy);

    void save(BookLoan bookLoan);

    void delete(BookLoan bookLoan);
}
