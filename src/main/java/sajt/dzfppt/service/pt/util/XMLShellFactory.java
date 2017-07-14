package sajt.dzfppt.service.pt.util;

import java.io.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;

public class XMLShellFactory
{
	public static String xmlCharset = "UTF-8";
	public static String rootElementName = "ROOT";
	private static final Log log=LogFactory.getLog(XMLShellFactory.class);
	private static XMLShellFactory factory;

	private XMLShellFactory()
	{
	}

	public static XMLShellFactory newInstance()
	{
		if (factory == null)
			factory = new XMLShellFactory();
		return factory;
	}

	@SuppressWarnings("rawtypes")
	public List generateDomainObject(String xml)
		throws Exception
	{
		IXMLShell xmlShell = new XMLShellImp();
		java.io.InputStream is = new ByteArrayInputStream(xml.getBytes(xmlCharset));
		List list = xmlShell.parseXML(is);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public boolean saveXml(OutputStream out, Object obj)
	{
		IXMLShell xmlShell;
		xmlShell = new XMLShellImp(rootElementName);
		Document document = null;
		log.info("--begin save xml to out--");
		if (obj == null){
			log.error("null obj param");
			return false;
		}
		try
		{
			document = xmlShell.genXMLRootEle(null);
			if (obj.getClass().isAssignableFrom(java.util.List.class) || obj.getClass().isAssignableFrom(java.util.ArrayList.class))
			{
				for (Iterator it = ((List)obj).iterator(); it.hasNext(); xmlShell.recurInsertEle(document.getRootElement(), it.next()));
			} else
			{
				xmlShell.recurInsertEle(document.getRootElement(), obj);
			}
			xmlShell.saveXML(document, out, xmlCharset);
			log.info("--finish save xml--\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public String getXml(Object object)
		throws Exception
	{
		if (object == null)
			return "";
		IXMLShell xmlShell = new XMLShellImp(rootElementName);
		Document document = xmlShell.genXMLRootEle(null);
		if (object.getClass().isAssignableFrom(java.util.List.class) || object.getClass().isAssignableFrom(java.util.ArrayList.class))
		{
			for (Iterator it = ((List)object).iterator(); it.hasNext(); xmlShell.recurInsertEle(document.getRootElement(), it.next()));
		} else
		{
			xmlShell.recurInsertEle(document.getRootElement(), object);
		}
		return document.asXML();
	}
}
