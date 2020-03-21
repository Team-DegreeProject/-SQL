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

    //查询
    public T select(V key) {
        T t = this.root.select(key);
        if (t == null) {
            System.out.println("Key " + key + " do not exist");
        }
        return t;
    }

    //范围查询
    public Node selectRange(V key) {
        Node t = this.root.selectRange(key);
        if (t == null) {
            System.out.println("Key " + key + " do not exist");
        }
        return t;
    }

    //插入
    public void insert(T value, V key) {
        if (key == null)
            return;
        Node<T, V> t = this.root.insert(value, key);
        if (t != null)
            this.root = t;
    }

    //删除
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

    public int getDataNumber(){
        LeafNode temp = this.getLeft();
        int count=0;
        while(temp!=null){
            count=count+temp.keyNumber;
            temp=temp.right;
        }
        return count;
    }

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


    public static int getOrder() {
        return Order;
    }

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