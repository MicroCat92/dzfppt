package sajt.dzfppt.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.CaConstant;
import com.aisino.PKCS7;

import sajt.dzfppt.dao.DsptxxDao;
import sajt.dzfppt.entity.Dsptxx;
import sajt.dzfppt.pojo.ResultData;
import sajt.dzfppt.service.IEntrance;
import sajt.dzfppt.service.impl.EntranceImpl;
import sajt.dzfppt.util.JsonUtil;
import util.GenerateXml;
import util.IOUtil;

@Controller
@RequestMapping("/hello2")
public class TestHelloWorld {

	@Resource(name = "entranceImpl")
	private IEntrance entrance;

	@RequestMapping("/index")
	public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("11111");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("name", "wangrui");

		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "getData")
	// ,produces = "application/json; charset=utf-8"
	public String getData() {

		try {
//			System.out.println(System.getProperty("java.library.path"));
//			final String trustsBytes = CaConstant.getProperty("PUBLIC_TRUSTS");
//			System.out.println(trustsBytes);
//			String decryptPFXBytes = CaConstant.getProperty("CLIENT_DECRYPTPFX");
//			System.out.println(decryptPFXBytes);
//			String decryptPFXKey = CaConstant.getProperty("CLIENT_DECRYPTPFX_KEY");
//			System.out.println(decryptPFXKey);

			// 前端传入 xml
			// 模拟 xml
		String xml = GenerateXml.shift_plus("E:\\gitProject\\dzfppt\\pt_mxxz4.xml");
//			String source = IOUtil.msgMachine("E:\\gitProject\\dzfppt\\pt_mxxz5.xml");
//			final PKCS7 pkcs7Client = new PKCS7(FileUtils.readFileToByteArray(new File(trustsBytes)),
//					FileUtils.readFileToByteArray(new File(decryptPFXBytes)), decryptPFXKey);
//			final byte[] encodeData = pkcs7Client.pkcs7Encrypt(source,
//					FileUtils.readFileToByteArray(new File(CaConstant.getProperty("PLATFORM_DECRYPTCER"))));
//			String xml = new String(Base64.encodeBase64(encodeData));
//			System.out.println("客户端加密:{" + xml + "}");
//			final String base64EncryptTxt = xml;
//			System.out.println(base64EncryptTxt);
			entrance.execute(xml);

			ResultData sj = new ResultData();
			// if ("1".equals(impType)) {
			// sj.setCode("10000");
			// } else {
			// sj.setCode("20000");
			// }
			// sj.setMsg("请求成功");
			// System.out.println(data.trim());
			// sj.setResult(data.trim());
			return JsonUtil.toJson(sj);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
