package jpapractice.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapractice.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findById(final Long memberId);
}
