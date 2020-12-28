package kr.co.sinbuya.www.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sinbuya.entity.DevelopTest;
import kr.co.sinbuya.www.service.TestService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired private TestService testService;

	@RequestMapping("")
	public String pages(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			@PathVariable(name = "view", required = false) String view,
			@RequestParam(value = "page", required = false) Integer page) {
		
		
		// 기본 인덱스 페이지

		DevelopTest entity = testService.findById(1L);
		
		System.out.println(entity.getContent());
	
		return "/default/index";
	}
	
	@RequestMapping(value = "/test.ajax", method = RequestMethod.POST)
	public ResponseEntity<String> contacts(ModelMap model, HttpServletRequest request, HttpServletResponse response
			,@RequestParam(value = "cid", required = false) Long cid) throws Exception {

		
		//작업내용	
		JSONObject object = new JSONObject();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(object.toString(), responseHeaders, HttpStatus.OK);
	}
	
}
