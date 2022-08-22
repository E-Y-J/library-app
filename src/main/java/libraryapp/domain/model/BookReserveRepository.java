package libraryapp.domain.model;

import org.springframework.data.repository.Repository;

public interface BookReserveRepository extends Repository<BookReserve, Long> {
    void save(BookReserve bookReserve);

    void delete(BookReserve bookReserve);
}
