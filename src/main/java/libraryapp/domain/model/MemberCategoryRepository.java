package libraryapp.domain.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MemberCategoryRepository extends Repository<MemberCategory, Long> {
    MemberCategory findById(Long id);
}
