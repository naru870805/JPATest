package kr.co.sinbuya.www.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sinbuya.www.service.TestService;

@Controller
@RequestMapping("")
public class PublishController {
	
	@Autowired private TestService testService;

	@RequestMapping("/publish")
	public String pages(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			@PathVariable(name = "view", required = false) String view,
			@RequestParam(value = "page", required = false) Integer page) {

		return "/publish/index";
	}
	
}
