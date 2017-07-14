package sajt.dzfppt.service.pt.util;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.*;
import org.dom4j.io.*;

public class XMLShellImp implements IXMLShell
{
	private static final Log log;
	private String rootNodeName;

	public XMLShellImp()
	{
		rootNodeName = XMLShellFactory.rootElementName;
	}

	public XMLShellImp(String rootEleName)
	{
		rootNodeName = XMLShellFactory.rootElementName;
		rootNodeName = rootEleName;
	}

	public Document genXMLRootEle(Document document)
	{
		if (document == null)
			document = DocumentHelper.createDocument();
		document.addElement(rootNodeName);
		return document;
	}

	@SuppressWarnings("rawtypes")
	public void recurInsertEle(Element parentE, Object object) throws Exception
	{
		String fieldName = null;
		Class cls = null;
		Method methodTitle = null;
		Element secondE = null;
		Element thirdE = null;
		Object tmpObj = null;
		if (parentE == null || object == null)
		{
			log.warn("null object");
			return;
		}
		cls = object.getClass();
		log.debug("class name:" + cls.getName());
		if (object == null)
		{
			log.warn("----empty object----");
			return;
		}
		if (java.util.List.class.isInstance(object))
		{
			List tList = (List)object;
			if (tList.size() == 0)
			{
				log.warn("----empty object----");
				return;
			}
			if ((tmpObj = tList.get(0)) != null)
			{
				secondE = parentE.addElement(getClsShortName(tmpObj.getClass()) + "S");
				secondE.addAttribute("class", getClsShortName(Array.newInstance(tmpObj.getClass(), 0).getClass()));
				secondE.addAttribute("size", (new StringBuffer(String.valueOf(tList.size()))).toString());
				for (Iterator it = tList.iterator(); it.hasNext(); recurInsertEle(thirdE, it.next()))
					thirdE = secondE.addElement(getClsShortName(tmpObj.getClass()));

			} else
			{
				log.warn("----empty list----");
			}
		} else
		if (cls.isArray())
		{
			fieldName = getClsShortName(cls.getComponentType());
			secondE = parentE.addElement(fieldName + "S");
			secondE.addAttribute("class", getClsShortName(cls));
			int len = 0;
			if (object != null && (len = Array.getLength(object)) > 0)
			{
				secondE.addAttribute("size", (new StringBuffer(String.valueOf(len))).toString());
				for (int j = 0; j < len; j++)
				{
					thirdE = secondE.addElement(fieldName);
					if (isPrimitiveCls(cls.getComponentType()))
						thirdE.setText(Array.get(object, j).toString());
					else
						recurInsertEle(thirdE, Array.get(object, j));
				}

			} else
			{
				secondE.addAttribute("size", "0");
			}
		} else
		{
			if (parentE.getParent() == null)
			{
				parentE = parentE.addElement(getClsShortName(cls));
				parentE.addAttribute("class", getClsShortName(cls));
			}
			Field field[] = cls.getDeclaredFields();
			for (int i = 0; i < field.length; i++)
			{
				fieldName = field[i].getName();
				log.debug("field name:" + fieldName);
				if (!fieldName.startsWith("class$") && !fieldName.equals("ZY") && !fieldName.equals("JYFW"))
				{
					methodTitle = cls.getMethod(getOnetoUpperCase(fieldName), new Class[0]);
					log.debug("method name:" + methodTitle);
					Object childObj = methodTitle.invoke(object, null);
					if (!isPrimitiveCls(field[i].getType()))
					{
						if (field[i].getType().isArray())
						{
							secondE = parentE.addElement(fieldName);
							secondE.addAttribute("class", getClsShortName(field[i].getType()));
							int len = 0;
							if (childObj != null && (len = Array.getLength(childObj)) > 0)
							{
								tmpObj = Array.get(childObj, 0);
								secondE.addAttribute("size", (new StringBuffer(String.valueOf(len))).toString());
								for (int j = 0; j < len; j++)
								{
									thirdE = secondE.addElement(getClsShortName(field[i].getType().getComponentType()));
									if (isPrimitiveCls(tmpObj.getClass()))
										thirdE.setText(Array.get(childObj, j).toString());
									else
										recurInsertEle(thirdE, Array.get(childObj, j));
								}

							} else
							{
								secondE.addAttribute("size", "0");
							}
						} else
						if (childObj != null)
						{
							secondE = parentE.addElement(fieldName);
							secondE.addAttribute("class", getClsShortName(field[i].getType()));
							recurInsertEle(secondE, childObj);
						}
					} else
					{
						secondE = parentE.addElement(fieldName);
						Object o = methodTitle.invoke(object, null);
						if (o != null)
							secondE.setText(o.toString());
					}
				}
			}

		}
	}

	private String getClsShortName(Class cls)
	{
		String clsShortName = null;
		if (cls == null)
			return null;
		int lastDotIndex = cls.getName().lastIndexOf(".");
		if (lastDotIndex < 0)
		{
			log.error("error");
			return null;
		} else
		{
			clsShortName = cls.getName().substring(lastDotIndex + 1);
			return clsShortName;
		}
	}

	private String getOnetoUpperCase(String str)
	{
		return "get" + firsttoUpperCase(str);
	}

	public String firsttoUpperCase(String str)
	{
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	private String getElementClassAtt(Element e)
	{
		for (Iterator attIt = e.attributeIterator(); attIt.hasNext();)
		{
			Attribute attri = (Attribute)attIt.next();
			if ("class".equals(attri.getName().trim()))
				if (attri.getValue().endsWith(";"))
					return "[Lsajt.dzfppt.service.pt.bean." + attri.getValue();
				else
					return "sajt.dzfppt.service.pt.bean." + attri.getValue();
		}

		return null;
	}

	private String getElementSizeAtt(Element e)
	{
		for (Iterator attIt = e.attributeIterator(); attIt.hasNext();)
		{
			Attribute attri = (Attribute)attIt.next();
			if ("size".equals(attri.getName().trim()))
				return attri.getValue();
		}

		return null;
	}

	private Object readEle(Element parentE, Class objCls)
		throws Exception
	{
		if (objCls.isArray() || objCls.isAssignableFrom(java.util.List.class) || objCls.isAssignableFrom(java.util.ArrayList.class))
		{
			log.error("parameters error");
			return null;
		}
		Object obj = objCls.newInstance();
		Field field[] = objCls.getDeclaredFields();
		String fieldName = null;
		for (Iterator it = parentE.elementIterator(); it.hasNext();)
		{
			Element ele = (Element)it.next();
			for (int i = 0; i < field.length; i++)
			{
				fieldName = field[i].getName();
				if (fieldName.startsWith("class$") || !fieldName.equals(ele.getName()))
					continue;
				field[i].setAccessible(true);
				recurSetObject(field[i], obj, ele);
				break;
			}

		}

		return obj;
	}

	public List recurReadEle(Element parentE)
		throws Exception
	{
		List list = new ArrayList();
		Class parentCls = null;
		int arraySize = 0;
		String tmpStr = null;
		String parentClsName = getElementClassAtt(parentE);
		if (parentClsName == null || parentClsName.equals(""))
		{
			log.warn("--error paramter---");
			return null;
		}
		parentCls = Class.forName(parentClsName);
		tmpStr = getElementSizeAtt(parentE);
		if (tmpStr != null && !tmpStr.equals(""))
			arraySize = (new Integer(tmpStr)).intValue();
		if (parentCls.isArray())
		{
			if (arraySize != 0)
			{
				int index = 0;
				Object object = Array.newInstance(parentCls.getComponentType(), arraySize);
				if (isPrimitiveCls(parentCls.getComponentType()))
				{
					for (Iterator firtIt = parentE.elementIterator(); firtIt.hasNext();)
					{
						Element secondE = (Element)firtIt.next();
						Array.set(object, index, secondE.getTextTrim());
						index++;
					}

				} else
				{
					index = 0;
					for (Iterator firtIt = parentE.elementIterator(); firtIt.hasNext();)
					{
						Element secondE = (Element)firtIt.next();
						Object secondObj = readEle(secondE, parentCls.getComponentType());
						Array.set(object, index, secondObj);
						index++;
					}

				}
				list.add(object);
			}
		} else
		{
			Object object = readEle(parentE, parentCls);
			list.add(object);
		}
		return list.size() != 0 ? list : null;
	}

	public void saveXML(Document document, File file, String encoding)
		throws IOException
	{
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = null;
		format.setEncoding(encoding);
		writer = new XMLWriter(new FileWriter(file), format);
		writer.write(document);
		writer.close();
	}

	public void saveXML(Document document, OutputStream out, String encoding)
		throws IOException
	{
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = null;
		format.setEncoding(encoding);
		writer = new XMLWriter(out, format);
		writer.write(document);
		writer.close();
	}

	public List parseXML(InputStream in)
		throws Exception
	{
		List list = new ArrayList();
		SAXReader sr = new SAXReader();
		Document doc = sr.read(in);
		Element root = doc.getRootElement();
		Element firstE;
		for (Iterator it = root.elementIterator(); it.hasNext(); list.add(recurReadEle(firstE)))
			firstE = (Element)it.next();

		return list;
	}

	public List parseXML(File file)
		throws Exception
	{
		List list = new ArrayList();
		SAXReader sr = new SAXReader();
		Document doc = sr.read(file);
		Element root = doc.getRootElement();
		Element firstE;
		for (Iterator it = root.elementIterator(); it.hasNext(); list.add(recurReadEle(firstE)))
			firstE = (Element)it.next();

		return list;
	}

	private void recurSetObject(Field field, Object object, Element element)
		throws Exception
	{
		String clsName = getElementClassAtt(element);
		List tList = null;
		log.debug("in recurSetObject element text = " + element.getTextTrim());
		if (field.getType().equals(Integer.TYPE))
			field.set(object, new Integer(element.getTextTrim()));
		else
		if (field.getType().equals(Double.TYPE))
			field.set(object, new Double(element.getTextTrim()));
		else
		if (field.getType().equals(Long.TYPE))
			field.set(object, new Long(element.getTextTrim()));
		else
		if (field.getType().equals(java.lang.String.class))
			field.set(object, element.getText().trim());
		else
		if (field.getType().isArray())
		{
			if (clsName != null)
			{
				tList = recurReadEle(element);
				if (tList != null)
					field.set(object, tList.get(0));
				else
					field.set(object, null);
			} else
			{
				field.set(object, null);
			}
		} else
		if (field.getType().equals(java.util.List.class) || field.getType().equals(java.util.ArrayList.class))
		{
			tList = recurReadEle(element);
			field.set(object, tList);
			log.warn("----warning 对象中没有对list做支持----");
		} else
		{
			tList = recurReadEle(element);
			if (tList != null)
				field.set(object, tList.get(0));
			else
				field.set(object, null);
		}
	}

	private static boolean isPrimitiveCls(Class cls)
	{
		return cls == java.lang.String.class || cls.isPrimitive();
	}

	public String getRootNodeName()
	{
		return rootNodeName;
	}

	public void setRootNodeName(String rootNodeName)
	{
		this.rootNodeName = rootNodeName;
	}

	public static void recurOutPut(Object obj)
	{
		Class cls = obj.getClass();
		if (java.util.List.class.isInstance(obj))
		{
			Object o;
			for (Iterator it = ((List)obj).iterator(); it.hasNext(); recurOutPut(o))
				o = it.next();

		} else
		if (cls.isArray())
		{
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++)
				recurOutPut(Array.get(obj, i));

		}
	}

	public static void testParse(String filePath, String encoding, String rootName)
	{
		File file = new File(filePath);
		IXMLShell xmlShell = new XMLShellImp(rootName);
		List list = new ArrayList();
		try
		{
			log.debug("--begin read xml--");
			list = xmlShell.parseXML(file);
			recurOutPut(list);
			log.debug("--finish read xml--");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	public static void testSeries(String filePath, String encoding, String rootName, Object obj)
	{
		File file = new File(filePath);
		IXMLShell xmlShell = new XMLShellImp(rootName);
		Document document = null;
		try
		{
			log.debug("--begin write xml--");
			document = xmlShell.genXMLRootEle(null);
			if (obj.getClass().isAssignableFrom(java.util.List.class) || obj.getClass().isAssignableFrom(java.util.ArrayList.class))
			{
				for (Iterator it = ((List)obj).iterator(); it.hasNext(); xmlShell.recurInsertEle(document.getRootElement(), it.next()));
			} else
			{
				xmlShell.recurInsertEle(document.getRootElement(), obj);
			}
			xmlShell.saveXML(document, file, encoding);
			log.debug("--finish write xml--\n");
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	public static void main(String args[])
	{
		String fileName = "D:/11.xml";
		String rootName = "root";
		String encodeName = "UTF-8";
		testParse(fileName, encodeName, rootName);
	}

	static 
	{
		log = LogFactory.getLog(XMLShellImp.class);
	}
}
