package sajt.dzfppt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloWorld {

		@RequestMapping("/index")
	    public ModelAndView Add(HttpServletRequest request,HttpServletResponse response){
	        System.out.println("11111");
	        
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("hello");
	        mv.addObject("name","wangrui");
	        return mv;
	    }
		
}
