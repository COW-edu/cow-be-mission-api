package jpapractice.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpapractice.member.controller.dto.request.UpdateMemberRequest;
import jpapractice.member.controller.dto.response.MemberResponse;
import jpapractice.member.controller.dto.request.MemberRequest;
import jpapractice.member.entity.Member;
import jpapractice.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/new")
	public MemberResponse create(@Valid @RequestBody MemberRequest memberRequest) {
		return memberService.join(memberRequest);
	}

	@GetMapping("/{memberId}")
	public MemberResponse find(@PathVariable Long memberId) {
		Member member = memberService.findOne(memberId);
		return MemberResponse.of(member);
	}

	@PatchMapping("/{memberId}/edit")
	public void updateMember(@RequestBody UpdateMemberRequest updateMemberRequest, @PathVariable Long memberId) {
		memberService.update(memberId, updateMemberRequest);
	}

}
