package libraryapp.domain.model;

import org.springframework.data.repository.Repository;

public interface MemberAccountRepository extends Repository<MemberAccount, Long> {
    MemberAccount findByMemberId(String memberId);
}
