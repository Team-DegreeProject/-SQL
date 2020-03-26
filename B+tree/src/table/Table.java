package table;

import table.BTree.BPlusTree;
import table.BTree.CglibBean;
import table.column.ColumnDescriptor;
import table.column.DataTypeDescriptor;
import table.type.SqlConstantImpl;

import java.util.HashMap;
import java.util.List;

public class Table extends SqlConstantImpl {
    private TableDescriptor td;
    private  BPlusTree tree;
    private HashMap propertyMap;
    public Table(){}
    public Table(TableDescriptor td,BPlusTree b){
        this.td=td;
        tree = new BPlusTree<>(4);;
    }
    public Table(TableDescriptor td) throws ClassNotFoundException {
        this.td=td;
        createTable(td);
    }

    public void setTableDescriptor(TableDescriptor td) { this.td = td; }

    public void setTree(BPlusTree tree) { this.tree = tree; }

    public TableDescriptor getTableDescriptor(){return td;}

    public BPlusTree setTree() { return this.tree; }

    public HashMap createTable(TableDescriptor table) throws ClassNotFoundException {
        propertyMap = new HashMap();
        ColumnDescriptorList list=table.getColumnDescriptorList();
        for(int i=0;i<list.size();i++){
            ColumnDescriptor cd=list.getColumnDescriptor(i);
            DataTypeDescriptor dtd=cd.getType();
            propertyMap.put(cd.getColumnName(), Class.forName(sqlMap.get(dtd.typeId)));
        }
        return propertyMap;
    }

    public boolean insertRows(List attributes, List types,List values,String primaryKey) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(attributes.size()!=values.size()){
            System.out.println("The number of attributes is not equal to the number of values.");
            return false;
        }
        for(int i=0;i<attributes.size();i++){
            int att=(int)types.get(i);
            Object obj = Class.forName(sqlMap.get(att)).newInstance();
            insertAnAttribute((String) attributes.get(i),obj,primaryKey);
        }
        return true;
    }
    public void insertAnAttribute(String attribute, Object value,String primaryKey){
        CglibBean bean = new CglibBean(propertyMap);
        bean.setValue(attribute, value);
        tree.insert(bean,(Integer) bean.getValue(primaryKey));
    }
}
