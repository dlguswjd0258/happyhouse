package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnAController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnaService;

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/{pageNo}/{key}/{word}")
	public ResponseEntity<Map<String,Object>> selectBoard(@PathVariable("pageNo") int pageNo, @PathVariable("key") String key,
			@PathVariable("word") String word) throws Exception {
		logger.debug("retrieveBoard - 호출");
		Map<String, Object> map = new HashMap<String, Object>();

		PageBean bean = new PageBean();
		bean.setKey(key);
		bean.setWord(word);
		bean.setPageNo(pageNo);
		map.put("bean", bean);

		List<QnADto> boards = qnaService.selectBoard(bean);
		map.put("boards", boards);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnADto.class)
	@GetMapping("{no}")
	public ResponseEntity<QnADto> selectBoardByNo(@PathVariable int no) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<QnADto>(qnaService.selectBoardByNo(no), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertBoard(@RequestBody QnADto qna, HttpSession session) {
		logger.debug("writeBoard - 호출");
		String userid = ((MemberDto) session.getAttribute("userinfo")).getUserId();
		qna.setWriter(userid);
		if (qnaService.insertBoard(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateBoard(@RequestBody QnADto qna) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + qna);

		if (qnaService.updateBoard(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		logger.debug("deleteBoard - 호출");
		if (qnaService.deleteBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 답글 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/reply/")
	public ResponseEntity<String> updateReply(@RequestBody QnADto qna) {
		logger.debug("updateReply - 호출");
		logger.debug("" + qna);
		if (qnaService.updateReply(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 답글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/reply/{no}")
	public ResponseEntity<String> deleteReply(@PathVariable int no) {
		logger.debug("deleteReply - 호출");

		if (qnaService.deleteReply(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}