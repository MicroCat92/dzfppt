package sajt.dzfppt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sajt.dzfppt.pojo.ResultData;
import sajt.dzfppt.util.JsonUtil;

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
		
		@ResponseBody
		@RequestMapping(value="getData")
		//,produces = "application/json; charset=utf-8"
		public String getData(@RequestParam("xmlData")String data,@RequestParam("impType")String impType){
			
			ResultData sj = new ResultData();
			if("1".equals(impType)){
				sj.setCode("10000");
			}else{
				sj.setCode("20000");
			}
			sj.setMsg("请求成功");
			System.out.println(data.trim());
			sj.setResult(data.trim());
			return JsonUtil.toJson(sj);
		}
		
}
