package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Ni Shengwu
 * @description This util finish works before and after operating file
 */
public class XmlUtils {
	
	private static String filename = "user.xml";
	
	//get Document object
	public static Document getDocument() throws DocumentException{
		
		URL url = XmlUtils.class.getClassLoader().getResource(filename);
		String realpath = url.getPath();
		SAXReader reader = new SAXReader();
		Document document = reader.read(realpath);
		return document;
	}
	
	//write document to xml file
	public static void WriteToXml(Document document) throws IOException, IOException {
		URL url = XmlUtils.class.getClassLoader().getResource(filename);
		String realpath = url.getPath();
		OutputFormat format = OutputFormat.createCompactFormat();
		XMLWriter writer = new XMLWriter(new FileOutputStream(realpath), format);
		writer.write(document);
		writer.close();
	}

}
