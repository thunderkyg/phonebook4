package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/pb")
public class PhoneController {

	// Field

	// Constructor

	// Method - G/S

	// Method - Ordinary
	@RequestMapping(value = "/test")
	public void test() {
		System.out.println("test");
	}

	//------------------------------ 리스트 ------------------------------//
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController.List");
		
		//Dao 사용
		PhoneDao phoneDao = new PhoneDao();
		
		//Dao 메소드로 데이터 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		
		//model담기 (택배박스 담기 ㅋ)
		model.addAttribute("personList", personList);
		
		return "/WEB-INF/views/list.jsp";
	}

	//------------------------------ Update ------------------------------//
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(@RequestParam("no") int no, Model model) {
		System.out.println("PhoneController.updateForm");
		
		//Dao
		PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = phoneDao.getPerson(no);
		
		//Add Attribute
		model.addAttribute("personVo", personVo);
		
		return "/WEB-INF/views/updateForm.jsp";
	}

	//------------------------------ Update Function ------------------------------//
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		
		// @ModelAttribute --> new PersonVo() 
		// --> 기본생성자 + setter
		System.out.println(personVo);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		return "redirect:./list";
	}
	
	//------------------------------ Write Form ------------------------------//
	
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	//------------------------------ Write Function ------------------------------//
	// Model Attribute
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {

		// @ModelAttribute --> new PersonVo() 
						// --> 기본생성자 + setter
		System.out.println(personVo);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		return "redirect:./list";
	}
	
	//------------------------------ Delete Function ------------------------------//
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(no);
		
		return "redirect:./list";
	}
	
	
////	  파라미터 하나씩 받을떄
//
//	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
//	public String write(@RequestParam("name") String name,
//						@RequestParam("hp") String hp,
//						@RequestParam(value="company", required=false, defaultValue="-1") String company) {
//		System.out.println(name);
//		System.out.println(hp);
//		System.out.println(company);
//
//		PersonVo personVo = new PersonVo(name, hp, company);
//		System.out.println(personVo);
//		return "";
//	}
//	
//	@RequestMapping(value="/board/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
//	public String read(@PathVariable("no") int boardNo) {
//		System.out.println("PathVariable [read]");
//		
//		return "";
//	}
}