package sample.xml_read;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.List;

/**
 * Created by 10673 on 2019/8/3.
 */
public class JDOM {

    public void parseSingle(Element element,String prefix){
        List<Element> childElements = element.getChildren();
        StringBuilder sbb = new StringBuilder();
        sbb.append(prefix+"->job{");
        for(Element celement:childElements){
            sbb.append(celement.getName()+","+celement.getValue()+",");
        }
        sbb.delete(sbb.lastIndexOf(","),sbb.length());
        sbb.append("}");
        System.out.println(sbb.toString());
    }
    public void parseGroup(List<Element> elements,String prefix){
        for(Element element:elements){
            if(element.getName().equals("group")){
                parseGroup(element.getChildren(),prefix+"->group("+element.getAttribute("name").getValue()+")");
            }else{
                parseSingle(element,prefix);
            }
        }
    }

    public void getJobs(String filePath,String prefix) throws Exception{
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new File(filePath));
        Element rootElement = document.getRootElement();
        prefix = prefix+"->group{"+rootElement.getAttribute("name").getValue()+"}";
        List<Element> elements = rootElement.getChildren();
        for(Element element:elements){

            if(element.getName().equals("group")){
                parseGroup(element.getChildren(),prefix+"->group("+element.getAttribute("name").getValue()+")");
            }else{
                parseSingle(element,prefix);
            }
        }

    }

    public static void main(String args[]) throws Exception{
        String filePath="src/main/resources/tss.xml";
        new JDOM().getJobs(filePath,"|");
    }

}
