package BTree;

//T为值
//V键值需要比较大小，所以要继承Comparable
public class BPlusTree <T, V extends Comparable<V>>{
    //B+树的阶
    private static int Order;
    //B+树节点拥有key的最大值
    private static int maxKeyNumber;
    //根节点
    private Node<T, V> root;

    //默认阶为3
    public BPlusTree(){
        this.Order=3;
        this.maxKeyNumber=4;
        this.root = new LeafNode<T, V>();
    }

    //构造器
    public BPlusTree(int Order){
        this.Order = Order;
        //因为插入节点过程中可能出现超过上限的情况,所以这里要加1
        this.maxKeyNumber = Order + 1;
        this.root = new LeafNode<T, V>();
    }

    //查询
    public T select(V key){
        T t = this.root.select(key);
        if(t == null){
//            System.out.println("Key "+key+" do not exist");
        }
        return t;
    }

    //插入
    public void insert(T value, V key){
        if(key == null)
            return;
        Node<T, V> t = this.root.insert(value, key);
        if(t != null)
            this.root = t;
    }

    //删除
    public void delete(V key){
        if(key == null){
            return;
        }
        this.root.delete(key);
    }
    public static int getOrder(){
        return Order;
    }

    public static int getmaxKeyNumber(){
        return maxKeyNumber;
    }
}
