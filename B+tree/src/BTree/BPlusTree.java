package BTree;

import java.util.ArrayList;
import java.util.List;

//T为值
//V键值需要比较大小，所以要继承Comparable
public class BPlusTree <T, V extends Comparable<V>> {
    //B+树的阶
    private static int Order;
    //B+树节点拥有key的最大值
    private static int maxKeyNumber;
    //根节点
    private Node<T, V> root;

    //默认阶为3
    public BPlusTree() {
        this.Order = 3;
        this.maxKeyNumber = 4;
        this.root = new LeafNode<T, V>();
    }

    //构造器
    public BPlusTree(int Order) {
        this.Order = Order;
        //因为插入节点过程中可能出现超过上限的情况,所以这里要加1
        this.maxKeyNumber = Order + 1;
        this.root = new LeafNode<T, V>();
    }

    /**
     * 查询
     * @param key
     * @return
     */
    public T select(V key) {
        T t = this.root.select(key);
        if (t == null) {
            System.out.println("Key " + key + " do not exist");
        }
        return t;
    }

    /**
     * 查询范围
     * @param key
     * @return
     */
    public Node selectRange(V key) {
        Node t = this.root.selectRange(key);
        if (t == null) {
            System.out.println("Key " + key + " do not exist");
        }
        return t;
    }

    /**
     * 插入
     * @param value
     * @param key
     */
    public void insert(T value, V key) {
        if (key == null)
            return;
        Node<T, V> t = this.root.insert(value, key);
        if (t != null)
            this.root = t;
    }

    /**
     * 删除
     * @param key
     */
    public void delete(V key) {
        if (key == null) {
            return;
        }
//        System.out.println("根节点key为:");
//        for (int j = 0; j < root.keyNumber; j++)
//            System.out.print(root.keys[j] + " ");
//        System.out.println();
        boolean isnull=this.root.delete(key);
        if(isnull){
            this.root=new LeafNode<T, V>();
        }
    }

    public LeafNode<T, V> getLeft() {
        LeafNode node = this.root.refreshLeft();
        return node;
    }

    /**
     * 此表中所存的行数
     * @return 行数
     */
    public int getDataNumber(){
        LeafNode temp = this.getLeft();
        int count=0;
        while(temp!=null){
            count=count+temp.keyNumber;
            temp=temp.right;
        }
        return count;
    }


    /**
     * 此表中所存的所有行
     * @return 行的list
     */
    public List<Object> getDatas() {
        List<Object> products = new ArrayList<>();
        LeafNode temp = this.getLeft();
        while (temp != null) {
            for (int j = 0; j < temp.keyNumber; j++) {
                products.add(temp.values[j]);
            }
            temp = temp.right;
        }
        return products;
    }

    /**
     * 返回所有叶结点
     * @return 所有叶结点的list
     */
    public  List<LeafNode> getLeafNodes(){
        List<LeafNode> leafnodes = new ArrayList<>();
        LeafNode temp = this.getLeft();
        while (temp != null) {
            leafnodes.add(temp);
            temp = temp.right;
        }
        return leafnodes;
    }

    /**
     * 大于
     * @param key
     * @return 返回大于此key的CglibBean
     */
    public List<Object> getBigDatas(V key) {
        LeafNode node=(LeafNode) selectRange(key);
        int low = 0;
        int up = node.keyNumber;
        int middle = (low + up) / 2;
        while(low < up){
            V middleKey = (V) node.keys[middle];
            if(key.compareTo(middleKey) == 0)
                break;
            else if(key.compareTo(middleKey) < 0)
                up = middle;
            else
                low = middle;
            middle = (low + up) / 2;
        }
        List<Object> products = new ArrayList<>();
        for(int i=middle;i<node.keyNumber;i++){
            products.add(node.values[i]);
        }
        LeafNode temp = null;
        if(node.right!=null){
            temp=node.right;
        }
        while (temp != null) {
            for (int j = 0; j < temp.keyNumber; j++) {
                products.add(temp.values[j]);
            }
            temp = temp.right;
        }
        return products;
    }

    /**
     * 小于
     * @param key
     * @return 返回小于此key的CglibBean
     */
    public List<Object> getSmallDatas(V key) {
        LeafNode node=(LeafNode) selectRange(key);
        int low = 0;
        int up = node.keyNumber;
        int middle = (low + up) / 2;
        while(low < up){
            V middleKey = (V) node.keys[middle];
            if(key.compareTo(middleKey) == 0)
                break;
            else if(key.compareTo(middleKey) < 0)
                up = middle;
            else
                low = middle;
            middle = (low + up) / 2;
        }
        List<Object> products = new ArrayList<>();
        for(int i=middle;i>=0;i--){
            products.add(node.values[i]);
        }
        LeafNode temp=null;
        if(node.left!=null){
             temp= node.left;
        }
        while (temp != null) {
            for (int j = temp.keyNumber-1; j >=0; j--) {
                products.add(temp.values[j]);
            }
            temp = temp.left;
        }
        return products;
    }


    /**
     * 大于且小于
     * @param small
     * @param big
     * @return 返回在范围内key的CglibBean
     */
    public List<Object> getMiddleDatas(V small,V big) {
        LeafNode Snode=(LeafNode) selectRange(small);
        LeafNode Bnode=(LeafNode) selectRange(big);
        int low1 = 0;
        int up1 = Snode.keyNumber;
        int middle1 = (low1 + up1) / 2;
        while(low1 < up1){
            V middleKey = (V) Snode.keys[middle1];
            if(small.compareTo(middleKey) == 0)
                break;
            else if(small.compareTo(middleKey) < 0)
                up1 = middle1;
            else
                low1 = middle1;
            middle1 = (low1 + up1) / 2;
        }

        int low2 = 0;
        int up2 = Bnode.keyNumber;
        int middle2 = (low2 + up2) / 2;
        while(low2 < up2){
            V middleKey = (V) Bnode.keys[middle2];
            if(big.compareTo(middleKey) == 0)
                break;
            else if(big.compareTo(middleKey) < 0)
                up2 = middle2;
            else
                low2 = middle2;
            middle2 = (low2 + up2) / 2;
        }

        List<Object> products = new ArrayList<>();

        if(Snode!=Bnode){
            for(int i=middle1;i<Snode.keyNumber;i++){
                products.add(Snode.values[i]);
            }
            LeafNode temp=null;
            if(Snode.left!=null && Snode.left!=Bnode){
                temp= Snode.left;
            }
            while (temp != null && temp!=Bnode) {
                for (int j = temp.keyNumber-1; j >=0; j--) {
                    products.add(temp.values[j]);
                }
                temp = temp.left;
            }
            for(int i=0;i<middle2;i++){
                products.add(Bnode.values[i]);
            }
            return products;
        }else{
            for(int i=middle1;i<middle2;i++){
                products.add(Snode.values[i]);
            }
            return products;
        }

    }

    /**
     * 每个叶结点的存储数量限制
     * @return 返回order
     */
    public static int getOrder() {
        return Order;
    }

    /**
     * 每个叶结点不能超过的存储数量，order+1
     * @return 返回maxKeyNumber
     */
    public static int getmaxKeyNumber() {
        return maxKeyNumber;
    }

    public void getNodes(Node temp) {
//        System.out.println("temp.keynumber:" + temp.keyNumber);
        for (int z = 0; z < temp.keyNumber; z++) {
            System.out.print(temp.keys[z] + " ");

        }
        System.out.println();
        if (temp instanceof NonLeafNode) {
            for (int i = temp.keyNumber - 1; i >= 0; i--) {
                getNodes(temp.childs[i]);
            }
        }
    }

    public Node<T, V> getRoot() {
        return root;
    }
}
