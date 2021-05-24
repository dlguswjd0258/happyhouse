package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.MemberService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService memberService;
	// 비밀번호 암호화를 위한 객체
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 로그인
	@PostMapping("/login")
	private ResponseEntity<String> login(@RequestBody MemberDto memberDto, HttpSession session) {
		String msg = "success";
		
		MemberDto dto = memberService.getMember(memberDto.getUserId());
		// 아이디 없음
		if(dto == null) {
			msg = "등록된 아이디가 없습니다.";
		}else if(passwordEncoder.matches(memberDto.getUserPwd(), dto.getUserPwd())) { // 로그인 성공
			dto.setUserPwd("");
			session.setAttribute("userinfo", dto);
			System.out.println(session.getAttribute("userinfo"));
		}else { // 
			msg = "비밀번호가 일치하지 않습니다.";
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	// 로그아웃
	@GetMapping("/logout")
	private ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// 아이디 체크
	@GetMapping("/{userId}")
	private ResponseEntity<String> checkId(@PathVariable("userId") String userId) {
		String msg = "";
		MemberDto memberDto = memberService.getMember(userId);
		if (memberDto != null)
			msg = "사용 중인 아이디입니다.";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	// 회원가입
	@PostMapping
	private ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
		memberDto.setUserPwd(passwordEncoder.encode(memberDto.getUserPwd()));
		memberService.registerMember(memberDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	// 회원 정보 조회 => 로그인 시 세션에 담아놨기 때문에 세션값 주기
	@GetMapping
	private ResponseEntity<MemberDto> memberInfo(HttpSession session) {
		System.out.println(session.getAttribute("userinfo"));
		return new ResponseEntity<MemberDto>((MemberDto)session.getAttribute("userinfo"),HttpStatus.OK);
	}
	
	// 회원 정보 수정
	@PutMapping
	private ResponseEntity<String> memberModify(@RequestBody MemberDto memberDto) {
		String pwd = memberDto.getUserPwd().trim();
		if(pwd != null && !"".equals(pwd)) {
			memberDto.setUserPwd(passwordEncoder.encode(pwd));
		}
		memberService.modifyMember(memberDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	// 회원 탈퇴
	@DeleteMapping
	private ResponseEntity<String> memberDelete(HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			memberService.deleteMember(memberDto.getUserId());
			session.invalidate();
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			System.out.println(memberDto);
			return new ResponseEntity<String>("로그인 해주세요.", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{pageNo}/{key}/{word}")
	@ResponseBody
	private ResponseEntity<Map<String,Object>> searchAll(@PathVariable("pageNo") int pageNo, @PathVariable("key") String key, @PathVariable("word") String word){
		Map<String, Object> map = new HashMap<String, Object>();
		
		PageBean bean = new PageBean();
		bean.setKey(key);
		bean.setWord(word);
		bean.setPageNo(pageNo);
		map.put("bean", bean);
		
		List<MemberDto> users = memberService.searchAll(bean);
		map.put("", users);
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
}
