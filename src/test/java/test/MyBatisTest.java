package test;

import java.rmi.RemoteException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sajt.dzfppt.dao.DsptxxDao;
import sajt.dzfppt.dao.NsrxxDao;
import sajt.dzfppt.entity.Dsptxx;
import sajt.dzfppt.service.impl.EntranceImpl;
import sajt.dzfppt.service.pt.cl.ProXmlPT;
import sajt.shdzfp.sl.service.SajtIssueInvoiceServicePortTypeProxy;
import util.GenerateXml;

//首先指定Junit的Runner
@RunWith(SpringJUnit4ClassRunner.class)
//指明配置文件所在
@ContextConfiguration(locations={"classpath:Spring-all-properties.xml", "classpath:spring-mvc.xml", "classpath:spring-mybatis.xml"})
//继承AbstractTransactionalJUnit4SpringContextTests来获取Spring上下文环境来获取Bean
public class MyBatisTest {
	
	@Autowired
	private DsptxxDao dsptxxDao;
	@Autowired
	private NsrxxDao nsrxxDao;
	
	private ProXmlPT proXmlPT;
	
	@Test
	public void test() throws RemoteException {
//		IEntrance entrance = new EntranceImpl();
//		
//		String con = GenerateXml.shift_plus("E:\\gitProject\\dzfppt\\pt_mxxz4.xml");
//		entrance.execute(con);
		
		String con = GenerateXml.shift_plus("E:\\gitProject\\dzfppt\\pt_mxxz4.xml");
//		String result = new SajtIssueInvoiceServicePortTypeProxy("http://fw1test.shdzfp.com:7500/axis2/services/SajtIssueInvoiceService").eiInterface(con);
		new EntranceImpl().test(con, dsptxxDao, nsrxxDao);
//		System.out.println(result);
	}
	

}
