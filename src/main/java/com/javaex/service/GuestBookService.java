package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> guestSelect(){
		return guestBookDao.guestSelect();
	}
	
	public int guestAdd(GuestBookVo gusetBookVo) {
		return guestBookDao.guestAdd(gusetBookVo);
	}
	
	public int guestDelete(int no) {
		return guestBookDao.guestDelete(no);
	}
	
	public GuestBookVo getGuest(int no) {
		return guestBookDao.getGuest(no);
	}
	
	
}
