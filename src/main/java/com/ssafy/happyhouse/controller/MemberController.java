package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 로그인
	@PostMapping("/login")
	private ResponseEntity<String> login(MemberDto memberDto, HttpSession session, Model model) {
		return new ResponseEntity<String>(memberService.login(memberDto), HttpStatus.OK);
	}

//	// 로그아웃
//	@GetMapping("/logout")
//	private String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
//
//	// 회원 정보 조회 => 로그인 시 세션에 담아놨기 때문에 그냥 페이지 이동만
//	@GetMapping("memberinfo")
//	private String memberInfo() {
//		return "userInfo";
//	}
//
//	// 회원 정보 수정
//	@PostMapping("/modify")
//	private String memberModify(MemberDto memberDto, HttpSession session) {
//		memberService.modifyMember(memberDto);
//		return "userInfo";
//	}
//
//	// 회원가입 폼으로 이동
//	@GetMapping("/registerForm")
//	private String registerForm() {
//		return "register";
//	}
//
//	// 아이디 체크
//	@GetMapping("/checkid/{userId}")
//	@ResponseBody
//	private ResponseEntity<String> checkId(@PathVariable("userId") String userId) {
//		String msg = "";
//		MemberDto memberDto = memberService.getMember(userId);
//		if (memberDto != null)
//			msg = "사용 중인 아이디입니다.";
//		return new ResponseEntity<String>(msg, HttpStatus.OK);
//	}
//
//	// 회원가입
//	@PostMapping("/register")
//	private String registerMember(MemberDto memberDto) {
//		memberService.registerMember(memberDto);
//		return "login";
//	}
//
//	// 회원 탈퇴
//	@DeleteMapping
//	private String memberDelete(HttpSession session) {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			memberService.deleteMember(memberDto.getUserId());
//			session.invalidate();
//		}
//
//		return "redirect:/";
//	}
//	
//	// 회원 관리 페이지로 이동
//	@GetMapping("/admin")
//	private String searchAllForm() {
//		return "adminuser";
//	}
//	
//	@GetMapping("/{pageNo}/{key}/{word}")
//	@ResponseBody
//	private ResponseEntity<Map<String,Object>> searchAll(@PathVariable("pageNo") int pageNo, @PathVariable("key") String key, @PathVariable("word") String word){
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		PageBean bean = new PageBean();
//		bean.setKey(key);
//		bean.setWord(word);
//		bean.setPageNo(pageNo);
//		map.put("bean", bean);
//		
//		List<MemberDto> users = memberService.searchAll(bean);
//		map.put("users", users);
//		
//		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//	}
	
}
