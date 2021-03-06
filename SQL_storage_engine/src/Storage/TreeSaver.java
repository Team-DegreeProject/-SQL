package Storage;

import BTree.BPlusTree;
import BTree.CglibBean;
import BTree.LeafNode;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class TreeSaver {
    BPlusTree BplusTree;
    String[] nodePool;

    public TreeSaver(BPlusTree T) {
        this.BplusTree = T;
    }

    public void SaveAsXML() throws Exception {
        List<LeafNode> leafNodes = BplusTree.getLeafNodes();

        //xml的一些部分
        String tableName = "Student Information";
        //1.生成一个根节点
        Element table = new Element("table");
        //2.为节点添加属性
        table.setAttribute("name", tableName);
        //3.生成一个document的对象
        Document document = new Document(table);

//        for (int i = 0; i < ss.length; i++) {
//            Element student = new Element("student");
//            table.addContent(student);
//            Element name = new Element("name").setText(ss[i].getName());
//            Element id = new Element("id").setText(ss[i].getId());
//            student.addContent(name);
//            student.addContent(id);
//        }


        for (int i = 0; i < leafNodes.size(); i++) {
            LeafNode positionNode = leafNodes.get(i);
            Object[] objects = positionNode.getValues();
            for (int j = 0; j < objects.length ; j++) {
                if(objects[j] != null){
                    CglibBean cglb = (CglibBean) objects[j];
                    Object cglbObject = cglb.getObject();
                    Class c = cglbObject.getClass();
                    String className = c.getSimpleName();
                    Field[] fields = c.getDeclaredFields();
                    HashMap<String, String> internalDatas = new HashMap<>();
                    for (int h = 0, len = fields.length; h < len; h++) {
                        // 对于每个属性，获取属性名
                        String varName = fields[h].getName().substring(12);
//                        System.out.println("the varName is :"+varName);
                        //获取每个方法名
                        Method m = (Method) c.getMethod("get" + XMLUtils.getMethodName(varName));
                        //获取这个方法返回的值，然后一起存起来
                        String varVal = (String) m.invoke(cglbObject).toString();
//                        System.out.println("the value of the data is: "+varVal);
                        //以一个hashmap存下来，变量名-数据
                        internalDatas.put(varName, varVal);
                    }


                    Element entity = new Element(XMLUtils.convertToLetter(className));
                    table.addContent(entity);

                    //利用迭代器来遍历hashmap
                    Iterator iter = internalDatas.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        String key = (String)entry.getKey();
                        String val = (String)entry.getValue();

                        //将对应的内容和key添加到xml中
                        Element XML_entity = new Element(key).setText(val);
                        entity.addContent(XML_entity);


                    }




                }
                }

            }
        Format format=Format.getCompactFormat();
        format.setIndent("");
        //生成不一样的编码
        format.setEncoding("GBK");
        //4.创建XMLOutputter的对象
        XMLOutputter outputter=new XMLOutputter(format);
        //5.利用outputter将document对象转换成xml文档

        //测试命名和创建文件夹
        String tn = "test";
        File targetFile = new File(tn+".xml");
        File test = new File("data/"+tn);
        //创建文件夹
        test.mkdir();
        System.out.println("the file path is: "+new File("").getAbsolutePath());
        outputter.output(document, new FileOutputStream(new File("data/"+tn+"/"+tn+".xml")));
        }



    }
