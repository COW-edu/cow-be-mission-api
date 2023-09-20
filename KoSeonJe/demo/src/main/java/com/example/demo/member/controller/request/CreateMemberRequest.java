package com.example.demo.member.controller.request;

import com.example.demo.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

  private String authId;

  private String name;

  public Member toEntity(){
    return Member.builder()
        .authId(authId)
        .name(name)
        .build();
  }
}
