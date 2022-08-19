package libraryapp.domain.model;

import org.springframework.data.repository.Repository;

public interface LoanPeriodRepository extends Repository<LoanPeriod, Long> {
    LoanPeriod findByMemberCategoryIdAndBookCategory(Long memberCategoryId, String bookCategory);
}
