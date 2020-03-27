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
    public Table(TableDescriptor td,BPlusTree b) throws ClassNotFoundException {
        this.td=td;
        tree = new BPlusTree<>(4);
        createTable(td);
    }
    public Table(TableDescriptor td) throws ClassNotFoundException {
        this.td=td;
        tree = new BPlusTree<>(4);;
        createTable(td);

    }

    public void setTableDescriptor(TableDescriptor td) { this.td = td; }

    public void setTree(BPlusTree tree) { this.tree = tree; }

    public TableDescriptor getTableDescriptor(){return td;}

    public BPlusTree getTree() { return this.tree; }

    public HashMap createTable(TableDescriptor table) throws ClassNotFoundException {
        propertyMap = new HashMap();
        ColumnDescriptorList list=table.getColumnDescriptorList();
        for(int i=0;i<list.size();i++){
            ColumnDescriptor cd=list.getColumnDescriptor(i);
            DataTypeDescriptor dtd=cd.getType();
            System.out.println(cd.getColumnName()+"--->"+sqlMap.get(dtd.typeId));
            propertyMap.put(cd.getColumnName(), Class.forName(sqlMap.get(dtd.typeId)));
        }
        return propertyMap;
    }

    public boolean insertRows(String[] attributes,List values,String primaryKey) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(attributes.length!=values.size()){
            System.out.println("The number of attributes is not equal to the number of values.");
            return false;
        }
        CglibBean bean = new CglibBean(propertyMap);
        for(int i=0;i<attributes.length;i++){
            bean.setValue(attributes[i], values.get(i));
            System.out.println(attributes[i]+"--->>"+values.get(i));
        }
        tree.insert(bean,(Integer) bean.getValue(primaryKey));
        return true;
    }

    public int size(){
        return tree.getDataNumber();
    }
}
