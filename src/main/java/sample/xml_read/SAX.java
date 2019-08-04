package sample.xml_read;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by 10673 on 2019/8/3.
 */
public class SAX  {
    class SAXHandler extends DefaultHandler{
        StringBuilder lineContent = new StringBuilder();
        StringBuilder sbb = new StringBuilder();
        String singleNodeValue = "";
        StringBuilder groupPrefix = new StringBuilder();
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            groupPrefix.append("|");
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(qName.equals("group")){
                groupPrefix.append("->group("+attributes.getValue("name")+")");
            }else if(qName.equals("job")){
                lineContent.append(groupPrefix+"->job{");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(qName.equals("name")){
                lineContent.append("name:"+singleNodeValue+",");
            }else if(qName.equals("command")){
                lineContent.append("command:"+singleNodeValue+"}");
            }
            if(!lineContent.toString().endsWith(",") && !lineContent.toString().equals("")){
                sbb.append(lineContent.toString()+"\n");
                lineContent = new StringBuilder();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            singleNodeValue = new String(ch,start,length);
        }

        public void showDetail(){
            System.out.println(sbb.toString());
        }
    }

    public void getJobs(String filePath) throws Exception{
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SAXHandler saxHandler = new SAXHandler();
        saxParser.parse(filePath,saxHandler);
        saxHandler.showDetail();
    }

    public static void main(String args []){
        long s = System.currentTimeMillis();
        String filePath = "src/main/resources/tss.xml";
        try {
            new SAX().getJobs(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long o = System.currentTimeMillis();
        System.out.println(o-s);
    }
}
