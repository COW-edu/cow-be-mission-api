package com.example.demo.member.controller.response;

import com.example.demo.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
  private String authId;

  private String name;

  @Builder
  private MemberResponse(String authId, String name) {
    this.authId = authId;
    this.name = name;
  }

  public static MemberResponse from(Member member){
    return MemberResponse.builder()
        .authId(member.getAuthId())
        .name(member.getName())
        .build();
  }
}
