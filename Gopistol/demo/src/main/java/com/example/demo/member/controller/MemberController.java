package com.example.demo.member.controller;

import com.example.demo.member.controller.request.CreateMemberRequest;
import com.example.demo.member.controller.request.UpdateMemberRequest;
import com.example.demo.member.controller.response.MemberResponseDTO;
import com.example.demo.member.domain.Member;
import com.example.demo.member.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("members") // 메소드 별로도 붙일 수 있지만, 공통적인 부분은 묶어놓는게 좋음
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public void createUser(@RequestBody CreateMemberRequest createMemberRequest) {
    memberService.createUser(createMemberRequest);
  }

  @PutMapping("/{id}")
  public void changeName(@PathVariable Long id,
      @RequestBody UpdateMemberRequest updateMemberRequest) throws NotFoundException {
    memberService.changeName(id, updateMemberRequest);
  }

  @GetMapping("/{id}")
  public MemberResponseDTO getMember(@PathVariable Long id) throws NotFoundException {
    return memberService.getMemberDTO(id);
  }

  // 모든 회원을 조회하여 Member 의 AuthId, Name 을 리스트로 반환
  @GetMapping
  public List<MemberResponseDTO> getAllMembers() {
    return memberService.getMembersDTO();
  }
}
