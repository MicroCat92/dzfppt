package sajt.dzfppt.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("tool")
public class TestToolController {
	
	@RequestMapping("/getindex")
    public ModelAndView Add(HttpServletRequest request,HttpServletResponse response){
        System.out.println("11111");
        ArrayList<String> boxs = new ArrayList<String>();
        boxs.add("发票开具");
        boxs.add("发票下载");
        boxs.add("发票推送");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("testtool");
        mv.addObject("boxs", boxs);
        return mv;
    }
}
