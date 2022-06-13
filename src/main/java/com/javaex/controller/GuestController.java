package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
//@RequestMapping(value = "/gbc")
public class GuestController {

	// 필드
	// 생성자
	// 메소드 - gs
	// 메소드-일반

	// ===================================== 목록폼 =====================================
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String List(Model model) {
		System.out.println("GuestController > addList()");

		// Dao를 통해서 personList(주소)를 가져온다.
		GuestBookDao guestBookDao = new GuestBookDao();
		List<GuestBookVo> guestList = guestBookDao.guestSelect();

		// ds 데이터보내기 --> request Attribute 에 넣는다.
		model.addAttribute("guestList", guestList);

		// list.jsp로 포워딩
		return "/WEB-INF/addList.jsp";
	}

	// ===================================== 등록 =====================================
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController > add");

		// dao로 저장하기
		GuestBookDao guestBookDao = new GuestBookDao();
		int count = guestBookDao.guestAdd(guestBookVo);
		System.out.println(count);

		// 리다이렉트
		return "redirect:/addList";
	}

	// 전화번호 삭제폼
	@RequestMapping(value = "/deleteForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@PathVariable("no") int no, Model model) {
		GuestBookDao guestBookDao = new GuestBookDao();
		GuestBookVo guestBookVo = guestBookDao.getGuest(no);
		System.out.println(guestBookVo);
		
		model.addAttribute("guestBookVo",guestBookVo);

		return "/WEB-INF/deleteForm.jsp";
	}

	// ===================================== 삭제 =====================================
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		GuestBookDao guestBookDao = new GuestBookDao();
		if (password.equals(guestBookDao.getGuest(no).getPassword())) {
			guestBookDao.guestDelete(no);
		} else {
			System.out.println("비밀번호가 틀립니다.");

		}
		// 리다이렉트 addList
		return "redirect:/addList";
	}

}
