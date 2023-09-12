package com.example.demo.member.service;

import com.example.demo.member.controller.request.CreateMemberRequest;
import com.example.demo.member.controller.request.UpdateMemberRequest;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor // final로 선언된 애들을 생성자 자동 만들어줌
public class MemberService {

  private final MemberRepository memberRepository;

  public void createUser(CreateMemberRequest createMemberRequest) {
    Member member = Member.create(createMemberRequest.getAuthId(), createMemberRequest.getName());
    memberRepository.save(member);
  }

  // 이름 수정
  public void changeName(Long id, UpdateMemberRequest updateMemberRequest)
      throws NotFoundException {
    Optional<Member> findMember = memberRepository.findById(id);
    Member member = findMember.orElseThrow(NotFoundException::new);
    member.changeName(updateMemberRequest.getName());
  }

  // 회원 목록 조회
  public Member findOne(Long id) throws NotFoundException {
    Optional<Member> findMember = memberRepository.findById(id);
    return findMember.orElseThrow(NotFoundException::new);
  }

  public List<Member> findAllMembers() {
    return memberRepository.findAll();
  }

}
