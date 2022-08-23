package libraryapp.domain.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookReserveRepository extends Repository<BookReserve, Long> {
    @Query("FROM BookReserve br WHERE br.account = :account")
    List<BookReserve> findManyBookReserveByMember(@Param("account")MemberAccount account);

    @Query("FROM BookReserve br WHERE br.book = :book")
    List<BookReserve> findManyBookReserveByBook(@Param("book")Book book);

    void save(BookReserve bookReserve);

    void delete(BookReserve bookReserve);
}
