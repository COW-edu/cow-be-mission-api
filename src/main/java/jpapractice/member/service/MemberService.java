package jpapractice.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.member.controller.dto.request.UpdateMemberRequest;
import jpapractice.member.controller.dto.response.MemberResponse;
import jpapractice.member.entity.Member;
import jpapractice.member.controller.dto.request.MemberRequest;
import jpapractice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberResponse join(MemberRequest memberRequest) {
		Member member = memberRequest.toEntity();
		memberRepository.save(member);
		return MemberResponse.of(member);
	}

	@Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
	}

	public Long update(Long memberId, UpdateMemberRequest updateMemberRequest) {
		Member member = findOne(memberId);
		member.updateMember(updateMemberRequest);
		return member.getId();
	}
}
