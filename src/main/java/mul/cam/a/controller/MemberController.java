package mul.cam.a.controller;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.Session;

import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;

@Controller
public class MemberController {
	// service 접근(생성)
	@Autowired
	MemberService service;
	
	@RequestMapping(value="login.do", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login " + new Date());
		
		return "login";
	}
	
	@RequestMapping(value="regi.do", method = RequestMethod.GET)
	public String regi() {
		System.out.println("MemberController regi " + new Date());
		
		return "regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
		System.out.println("MemberController idcheck " + new Date());
		
		boolean isS = service.idCheck(id);
		if(isS == true) {	// id가 있음
			return "NO";
		}
		
		return "YES";	// id가 없음
	}
	
	@RequestMapping(value="regiAf.do", method = RequestMethod.POST)
	public String regiAf(Model model, MemberDto dto) {
		System.out.println("MemberController regiAf " + new Date());
		
		boolean isS = service.addMember(dto);
		String message = "";
		if(isS) {
			message = "MEMBER_ADD_YES";
		} else {
			message = "MEMBER_ADD_NO";
		}
		model.addAttribute("message", message);
		
		return "message";
	}
	
	@RequestMapping(value="loginAf.do", method = RequestMethod.POST)
	public String loginAf(HttpServletRequest req, Model model, MemberDto dto) {	// request가 필요할 때 언제든지 추가 가능
		System.out.println("MemberController loginAf " + new Date());
		
		MemberDto mem = service.login(dto);
		String msg = "";
		if(mem != null) {	// login 성공
			req.getSession().setAttribute("login", mem);		// 세션 저장	
			req.getSession().setMaxInactiveInterval(60*60*2);	// 세션 종료(2시간)
			msg = "MEMBER_LOGIN_YES";
		} else {
			msg = "MEMBER_LOGIN_NO";
		}
		model.addAttribute("login", msg);
		
		return "message";
	}
}
