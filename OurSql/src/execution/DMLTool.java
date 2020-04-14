package execution;

import javafx.scene.control.Tab;
import parsing.Token;
import table.ColumnDescriptorList;
import table.Table;
import table.TableDescriptor;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static parsing.SqlParserConstants.*;

public class DMLTool {
//    public static void andCondition(HashMap condition, char t, int num){
//        if(t=='>'){
//            if(condition.get('>')==null){
//                condition.put('>',num);
//            }else{
//                int g=(int)condition.get('>');
//                if(num>g){
//                    condition.replace('>',num);
//                }
//            }
//        }else if(t=='<'){
//            if(condition.get('<')==null){
//                condition.put('<',num);
//            }else{
//                int g=(int)condition.get('<');
//                if(num<g){
//                    condition.replace('<',num);
//                }
//            }
//        }else if(t=='='){
//            if(condition.get('=')==null){
//                condition.put('=',num);
//            }else{
//                System.out.println("Warning:There are two =");
//            }
//        }
//
//    }
//
//    public static void orCondition(HashMap condition,char t,int num){
//        if(t=='>'){
//            if(condition.get('>')==null){
//                condition.put('>',num);
//            }else{
//                int g=(int)condition.get('>');
//                if(num<g){
//                    condition.replace('>',num);
//                }
//            }
//        }else if(t=='<'){
//            if(condition.get('<')==null){
//                condition.put('<',num);
//            }else{
//                int g=(int)condition.get('<');
//                if(num>g){
//                    condition.replace('<',num);
//                }
//            }
//        }else if(t=='='){
//            if(condition.get('=')==null){
//                condition.put('=',num);
//            }else{
//                System.out.println("Warning:There are two =");
//            }
//        }
//    }


    public static SqlType convertToValue(String att, String str, HashMap propertyMap,ColumnDescriptorList columnDescriptorList) throws Exception {
        Class c= (Class) propertyMap.get(att);
        SqlType value=(SqlType)c.newInstance();
        value.setValue(str);
        ColumnDescriptor cd=columnDescriptorList.getColumnDescriptor(att);
        DataTypeDescriptor dataTypeDescriptor=cd.getType();
        if(dataTypeDescriptor.getScale()!=-1){
            value.setScale(dataTypeDescriptor.getScale());
        }
        if(dataTypeDescriptor.getPrecision()!=-1){
            value.setPrecision(dataTypeDescriptor.getPrecision());
        }
        value.updateValue();
        return value;
    }




    public static ColumnDescriptor analyseOneRow(int k, List tokens,int position){
        DataTypeDescriptor dataType=null;
        ColumnDescriptor column=new ColumnDescriptor(position);
        boolean comment=false;
        String columnName=null;
        if(k==0){
            columnName=((Token)tokens.get(1)).image;
            dataType= new DataTypeDescriptor( ((Token)tokens.get(2)).kind  );
            for(int i=3;i<tokens.size();i++){
                Token t= (Token) tokens.get(i);
                if(comment){
                    column.setComment(t.image);
                    comment=false;
                }else {
                    comment = setType(dataType, t, column);
                }
            }
        }else if(k==1){
            columnName=((Token)tokens.get(0)).image;
            dataType= new DataTypeDescriptor( ((Token)tokens.get(1)).kind  );
            for(int i=2;i<tokens.size();i++){
                Token t= (Token) tokens.get(i);
                if(comment){
                    column.setComment(t.image);
                    comment=false;
                }else {
                    comment = setType(dataType, t, column);
                }
            }
        }
        column.setColumnName(columnName);
        column.setColumnType(dataType);
        return column;
    }

    public static boolean setType(DataTypeDescriptor d, Token t, ColumnDescriptor cd){
        if(t.kind==PRIMARY_KEY){
            d.setPrimaryKey(true);
        }else if(t.kind==NOT_NULL){
            d.setNullable(false);
        }else if(t.kind==NUMBER){
            if(d.getScale()==-1){
                d.setScale(Integer.parseInt(t.image));
            }else{
                d.setPrecision(Integer.parseInt(t.image));
            }
        }else if(t.kind==AUTO_INCREMENT){
            cd.setAutoincInc(true);
        }else if(t.kind==COMMENT){
            return true;
        }
        return false;
    }

    public static String removeQutationMark(String str){
        str=str.substring(1,str.length()-1);
        return str;
    }


    public static HashMap selectNewPropertyMap(HashMap propertyMap,List<List<Token>> tokens){
        HashMap newProperty=new HashMap();
        for(int i=0;i<tokens.size();i++){
            String name=tokens.get(i).get(0).image;
            Object o=propertyMap.get(name);
            newProperty.put(name,o);
        }
        Object pk=propertyMap.get("primary key");
        newProperty.put("primary key",pk);
        return newProperty;
    }

    //select中对于列操作的支持函数
    public static TableDescriptor changeTableDescriptor(TableDescriptor td,List<List<Token>> tokens){
        ColumnDescriptorList list=td.getColumnDescriptorList();
        ColumnDescriptor pk=list.getColumnDescriptor("primary key");
        ColumnDescriptorList newList=new ColumnDescriptorList();
        newList.add(pk);
        for(int i=0;i<list.size();i++){
            String name=list.elementAt(i).getColumnName();
            for(int j=0;j<tokens.size();j++){
                String com=tokens.get(j).get(0).image;
                if(name.equals(com)){
                    newList.add(list.elementAt(i));
                    break;
                }
            }
        }
        newList.printColumnDescriptorList();
//        System.out.println(newList.);
        TableDescriptor tableDescriptor=new TableDescriptor(td.getName(),td.getSchema(),newList,td.getPrimaryKey());
        return tableDescriptor;
    }

    //    public void setTablePrimaryKey(TableDescriptor td){
//        ColumnDescriptorList pk=td.getPrimaryKey();
//        pk=new ColumnDescriptorList();
//        ColumnDescriptorList cdl=td.getColumnDescriptorList();
//        for(int i=0;i<cdl.size();i++){
//            ColumnDescriptor c=cdl.elementAt(i);
//            boolean b=c.getType().isPrimaryKey();
//            if(b){
//                pk.add(c);
//            }
//        }
//    }
}
