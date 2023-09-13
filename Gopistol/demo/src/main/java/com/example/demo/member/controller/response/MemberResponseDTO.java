package com.example.demo.member.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class MemberResponseDTO {

  String authId;
  String name;
}
