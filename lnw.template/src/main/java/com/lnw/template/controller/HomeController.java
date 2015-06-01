package com.lnw.template.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lnw.template.service.HomeService;

@RequestMapping(value="/base/*")
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "home")
	public void home(Locale locale, HttpServletRequest request, @RequestParam Map<String, Object> params, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
	}
	
	//데이터 json요청
	@RequestMapping(value = "getBase.json")
	public void getBase(Locale locale, HttpServletRequest request, @RequestParam Map<String, Object> params, Model model) throws Exception {
		logger.info("정보");
		
		//service 호출
		List list = homeService.getBaseList(params);
		model.addAttribute("list", list);
	}
	
}
