package Storage;

import BTree.BPlusTree;
import BTree.CglibBean;
import BTree.LeafNode;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TreeLoader {

    public TreeLoader() {

    }

    public BPlusTree loadFromFile(String fileName) throws JDOMException, IOException, ClassNotFoundException {
        BPlusTree<CglibBean,String> resultTree = new BPlusTree<>();
        SAXBuilder saxBuilder = new SAXBuilder();
        //你也可以将demo.xml放在resources目录下，然后通过下面方式获取
        //InputStream resourceAsStream = JDOMParseXml.class.getClassLoader().getResourceAsStream("demo.xml");
        Document document1 = saxBuilder.build(new File("test.xml"));
        Element rootElement = document1.getRootElement();
        List<Element> elementList = rootElement.getChildren();
        for (Element element : elementList) {
            //获取所有的子节点
            List<Element> innerElementList = element.getChildren();
            //建立一个哈希map
            HashMap propertyMap = new HashMap();
            HashMap valueMap = new HashMap();
            for (Element innerElement : innerElementList) {
                String varName = innerElement.getName();
                String varVal = innerElement.getValue();
                valueMap.put(varName,varVal);
//                System.out.println("--------------------------");
//                System.out.println("the name is: "+varName + " - the value is:"+varVal);
                propertyMap.put(varName, Class.forName(varVal.getClass().getName()));
            }
            //property map已经处理完毕
            CglibBean b = new CglibBean(propertyMap);

            Iterator iter = valueMap.keySet().iterator();
            while (iter.hasNext()) {
                String valueName = (String) iter.next();
                b.setValue(valueName,valueMap.get(valueName));
                }
            resultTree.insert(b, (String) b.getValue("id"));



            }
        return resultTree;
        }


    }
