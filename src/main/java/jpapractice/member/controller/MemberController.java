package jpapractice.member.controller;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpapractice.member.controller.dto.request.UpdateMemberRequest;
import jpapractice.member.entity.Member;
import jpapractice.member.controller.dto.request.MemberRequest;
import jpapractice.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/new")
	public String create(@RequestBody MemberRequest memberRequest) {
		memberService.join(memberRequest);
		return "Post create member ok";
	}

	@GetMapping("/{memberId}")
	public String find(@PathVariable Long memberId) {
		Member member = memberService.findOne(memberId);
		return member.getName();
	}

	@PatchMapping("/{memberId}/edit")
	public void updateMember(@RequestBody UpdateMemberRequest updateMemberRequest, @PathVariable Long memberId) {
		memberService.update(memberId, updateMemberRequest);
	}

}
