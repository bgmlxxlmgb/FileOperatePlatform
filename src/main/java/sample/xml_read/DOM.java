package sample.xml_read;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by 10673 on 2019/8/3.
 */
public class DOM {
    //document解析器工厂
    private static DocumentBuilderFactory docBuiFactory = null;
    //document解析器可以通过documentBuiderFactory的newInstance()函数获取
    private static DocumentBuilder docBuilder = null;
    //document对象可以通过documentBuilder的newDocumentBuilder()函数获取
    private static Document doc = null;

    static {
        try {
            //初始化documentBuilderFactory
            docBuiFactory = DocumentBuilderFactory.newInstance();    //newInstance通过反射机制创建DocumentBuilderFactory的实现类
            //初始化documentBuilder
            docBuilder = docBuiFactory.newDocumentBuilder();    //通过DocumentBuilderFactoryImpl的newDocumentBuilder()函数返回DocumentBuilder对象
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void parseGroup(NodeList cNodeList,String prefix){
        //System.out.println("处理组：");
        for(int i=0;i<cNodeList.getLength();i++){
            Node cNode = cNodeList.item(i);
            String cNodeName = cNode.getNodeName();
            if(cNodeName.equals("group")){
                parseGroup(cNode.getChildNodes(),(prefix+"->group{"+cNode.getAttributes().getNamedItem("name").getTextContent()+"}"));
            }else if(!cNodeName.equals("#text")){
                parseSingle(cNode,prefix);
            }
        }
    }
    public static void parseSingle(Node sNode,String prefix){
        //System.out.println("开始处理单一节点：");
        NodeList cNodeList = sNode.getChildNodes();
        StringBuilder sbb = new StringBuilder();
        sbb.append(prefix+"->job{");
        for(int j=0;j<cNodeList.getLength();j++){
            Node cNode=cNodeList.item(j);
            if(!cNode.getNodeName().equals("#text")){
                sbb.append(cNode.getNodeName()+":"+cNode.getTextContent()+",");
            }
        }
        sbb.delete(sbb.lastIndexOf(","),sbb.length());
        sbb.append("}");
        System.out.println(sbb.toString());
    }

    public void getJobs(String fileURL) throws Exception{
        //将给定的url的内容解析为一个xml文档，并返回document对象
        doc = docBuilder.parse(fileURL);
        //按顺序获取xml内所有book元素节点
        Element rootElement = doc.getDocumentElement();
        String rootElementName = rootElement.getTagName();
        if(rootElementName.equals("group")){
            parseGroup(doc.getChildNodes(),"|");
        }else{
            parseSingle(doc,"|");
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/tss.xml";
        try {
           new DOM().getJobs(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
