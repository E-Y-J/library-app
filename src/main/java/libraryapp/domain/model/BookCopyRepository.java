package libraryapp.domain.model;

import org.springframework.data.repository.Repository;

public interface BookCopyRepository extends Repository<BookCopy, Long> {
    BookCopy findByBarcode(String barcode);
}
