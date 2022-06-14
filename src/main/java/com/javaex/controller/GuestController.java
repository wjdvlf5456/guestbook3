package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
//@RequestMapping(value = "/gbc")
public class GuestController {

	// 필드
	@Autowired
	private GuestBookService guestBookService;
	// 생성자
	// 메소드 - gs
	// 메소드-일반

	// ===================================== 목록폼 =====================================
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String List(Model model) {
		System.out.println("GuestController > addList()");

		// Dao를 통해서 guestList(주소)를 가져온다.
		List<GuestBookVo> guestList = guestBookService.guestSelect();

		// ds 데이터보내기 --> model Attribute 에 넣는다.
		model.addAttribute("guestList", guestList);

		// list.jsp로 포워딩
		return "addList";
	}

	// ===================================== 등록 =====================================
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController > add");

		// dao로 저장하기
		int count = guestBookService.guestAdd(guestBookVo);
		System.out.println(count);

		// 리다이렉트
		return "redirect:/addList";
	}

	// 방명록 삭제폼
	@RequestMapping(value = "/deleteForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@PathVariable("no") int no, Model model) {
		GuestBookVo guestBookVo = guestBookService.getGuest(no);
		System.out.println(guestBookVo);
		
		model.addAttribute("guestBookVo",guestBookVo);

		return "deleteForm";
	}

	// ===================================== 삭제 =====================================
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		if (password.equals(guestBookService.getGuest(no).getPassword())) {
			guestBookService.guestDelete(no);
		} else {
			System.out.println("비밀번호가 틀립니다.");

		}
		// 리다이렉트 addList
		return "redirect:/addList";
	}

}
