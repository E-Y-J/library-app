package libraryapp.domain.model;

import org.springframework.data.repository.Repository;

public interface MemberCategoryRepository extends Repository<MemberCategory, Long> {
    MemberCategory findById(Long id);
}
