package sample.xml_read;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.File;
import java.util.Iterator;

/**
 * Created by 10673 on 2019/8/3.
 */
public class DOM4j {

    public void parseGroup(Iterator iterator, String prefix){
        StringBuilder stringBuilder = new StringBuilder();
        Element element;
        while((element=(Element)iterator.next()) !=null){
            if(element.getName().equals("group")){
                parseGroup(element.elementIterator(),prefix+"->group("+element.attribute("name")+")");
            }else{
                parseSingle(element,prefix);
            }
        }
    }

    public void parseSingle(Element element,String prefix){
        Iterator iterator = element.elementIterator();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("->job{");
        Element cElement;
        while((cElement = (Element)iterator.next())!=null){
            stringBuilder.append(cElement.getName()+":"+cElement.getStringValue()+",");
        }
        stringBuilder.delete(stringBuilder.toString().lastIndexOf(","),stringBuilder.length());
        stringBuilder.append("}");

    }

    public void getJobs(String filePath,String prefix) throws Exception{
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(filePath));
        Element rootElement = document.getRootElement();
        if(rootElement.getName().equals("group")){
            parseGroup(rootElement.elementIterator(),prefix+"->group("+rootElement.attribute("name").getValue()+")");
        }else{
            parseSingle(rootElement,prefix);
        }
    }
    public static void main(String args[]) throws Exception{
        String filePath="src/main/resources/tss.xml";
        new DOM4j().getJobs(filePath,"|");
    }
}
