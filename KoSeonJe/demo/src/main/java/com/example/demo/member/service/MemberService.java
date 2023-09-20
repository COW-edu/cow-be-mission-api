package com.example.demo.member.service;

import com.example.demo.member.controller.request.CreateMemberRequest;
import com.example.demo.member.controller.request.UpdateMemberRequest;
import com.example.demo.member.controller.response.MemberResponse;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public void createUser(CreateMemberRequest createMemberRequest) {
    Member member = createMemberRequest.toEntity();
    memberRepository.save(member);
  }

  public void updateUserInfo(Long memberId, UpdateMemberRequest updateMemberRequest) {
    Member member = findOneUser(memberId);
    member.update(updateMemberRequest.getAuthId(), updateMemberRequest.getName());
  }

  @Transactional(readOnly = true)
  public MemberResponse getMemberInfo(Long memberId) {
    Member member = findOneUser(memberId);
    return MemberResponse.from(member);
  }

  @Transactional(readOnly = true)
  public Member findOneUser(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
  }
}
