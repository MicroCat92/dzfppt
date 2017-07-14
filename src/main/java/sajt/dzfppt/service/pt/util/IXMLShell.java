package sajt.dzfppt.service.pt.util;

import java.io.*;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;

public interface IXMLShell
{

	@SuppressWarnings("rawtypes")
	public abstract List parseXML(InputStream inputstream)
		throws Exception;

	@SuppressWarnings("rawtypes")
	public abstract List parseXML(File file)
		throws Exception;

	public abstract void saveXML(Document document, File file, String s)
		throws IOException;

	public abstract void saveXML(Document document, OutputStream outputstream, String s)
		throws IOException;

	public abstract Document genXMLRootEle(Document document);

	public abstract void recurInsertEle(Element element, Object obj)
		throws Exception;

	@SuppressWarnings("rawtypes")
	public abstract List recurReadEle(Element element)
		throws Exception;
}
