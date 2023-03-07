package mul.cam.a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.Session;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Controller
public class BbsController {
	@Autowired
	BbsService service;
	
	@GetMapping(value="bbslist.do")
	public String bbslist(BbsParam param, Model model) {
		
		// 글의 시작과 끝
		int pn = param.getPageNumber();
		int start = 1 + pn * 10;
		int end = (pn + 1) * 10;
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> list = service.bbslist(param);
		int len = service.getAllBbs(param);
		
		int pageBbs = len / 10;		// 25/10 -> 2
		if((len % 10)>0) {
			pageBbs += 1;
		}
		
		if(param.getChoice() == null || param.getChoice().equals("")
			|| param.getSearch() == null || param.getSearch().equals("")) {
			param.setChoice("검색");
			param.setSearch("");
		}
		
		model.addAttribute("bbslist", list);		// 게시판 리스트
		model.addAttribute("pageBbs", pageBbs);		// 총 페이지 수
		model.addAttribute("pageNumber", param.getPageNumber());	// 현재 페이지
		model.addAttribute("choice", param.getChoice());	// 검색 카테고리
		model.addAttribute("search", param.getSearch());	// 검색어
		
		return "bbslist";
	}
	
	@GetMapping(value="bbsdetail.do")
	public String bbsdetail(int seq, Model model) {
		
		BbsDto bbsdetail = service.getBbsdetail(seq);
		service.readcount(seq);
		model.addAttribute("bbsdetail", bbsdetail);
		
		return "bbsdetail";
	}
	
	@GetMapping(value="bbsWrite.do")
	public String bbswrite() {
		return "bbsWrite";
	}
	
	@PostMapping(value="bbsWriteAf.do")
	public String bbsWriteAf(BbsDto dto, Model model) {
		boolean isS = service.bbswrite(dto);
		String msg = "BBS_ADD_OK";
		if(!isS) {
			msg = "BBS_ADD_NO";
		}
		model.addAttribute("bbswrite", msg);
		return "message";
	}
	
	@GetMapping(value="updateBbs.do")
	public String updateBbs(int seq, Model model) {
		BbsDto dto = service.getBbsdetail(seq);
		
		model.addAttribute("seq", seq);
		model.addAttribute("dto", dto);
		return "updateBbs";
	}
	
	@PostMapping(value="updateBbsAf.do")
	public String updateBbsAf(BbsDto dto, Model model) {
		boolean isS = service.updateBbs(dto);
		String msg = "BBS_UPDATE_OK";
		if(!isS) {
			msg = "BBS_UPDATE_NO";
		}
		model.addAttribute("bbsupdate", msg);
		return "message";
	}
	
	@GetMapping(value="deleteBbs.do")
	public String deleteBbs(int seq, Model model) {
		boolean isS = service.deleteBbs(seq);
		String msg = "BBS_DELETE_OK";
		if(!isS) {
			msg = "BBS_DELETE_NO";
		}
		model.addAttribute("bbsdelete", msg);
		return "message";
	}
	
	@GetMapping(value="answer.do")
	public String answer(int seq, Model model) {
		BbsDto dto = service.getBbsdetail(seq);
				
		model.addAttribute("dto", dto);
		return "answer";
	}
	
	@PostMapping(value="answerAf.do")
	public String answerAf(int seq, BbsDto dto, Model model) {
		boolean isS = service.insertAnswer(dto);
		String msg = "";
		if(isS) {
			service.updateAnswer(seq);
			msg = "BBS_ANSWER_OK";
		} else {
			msg = "BBS_ANSWER_NO";
		}
		model.addAttribute("answer", msg);
		return "message";
	}
	
	@RequestMapping(value="commentWriteAf.do", method=RequestMethod.POST)
	public String commentWriteAf(BbsComment bbs) {
		boolean isS = service.insertComment(bbs);
		if(isS) {
			System.out.println("댓글작성에 성공했습니다");
		} else {
			System.out.println("댓글작성에 실패했습니다");			
		}
		
		return "redirect:bbsdetail.do?seq=" + bbs.getSeq();
	}
	
	@ResponseBody
	@RequestMapping(value="commentList.do", method=RequestMethod.GET)
	public List<BbsComment> commentList(int seq) {
		List<BbsComment> list = service.getCommentList(seq);
		return list;
	}
	
	
}
