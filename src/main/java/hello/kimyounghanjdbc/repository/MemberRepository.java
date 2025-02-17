package hello.kimyounghanjdbc.repository;

import hello.kimyounghanjdbc.domain.Member;

public interface MemberRepository {
    Member save(Member member);

    Member findById(String memberId);

    void update(String memberId, int money);

    void delete(String memberId);
}
