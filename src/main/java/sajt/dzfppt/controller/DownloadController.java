package sajt.dzfppt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("download")
public class DownloadController {
	
	@RequestMapping("/getindex")
    public ModelAndView Add(HttpServletRequest request,HttpServletResponse response){
        System.out.println("11111");
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("download");
        return mv;
    }
}
