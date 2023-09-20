package com.example.demo.member.controller;

import com.example.demo.member.controller.request.CreateMemberRequest;
import com.example.demo.member.controller.request.UpdateMemberRequest;
import com.example.demo.member.controller.response.MemberResponse;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @PostMapping
  public void createUser(@RequestBody CreateMemberRequest createMemberRequest) {
    memberService.createUser(createMemberRequest);
  }

  @PutMapping(value = "/{memberId}")
  public void updateUserInfo(
      @PathVariable Long memberId,
      @RequestBody UpdateMemberRequest updateMemberRequest) {
    memberService.updateUserInfo(memberId, updateMemberRequest);
  }

  @GetMapping(value = "/{memberId}")
  public MemberResponse getMemberInfo(@PathVariable Long memberId) {
    return memberService.getMemberInfo(memberId);
  }
}
