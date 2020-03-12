class LeafNode <T, V extends Comparable<V>> extends Node<T, V> {

    protected Object values[];
    protected LeafNode left;
    protected LeafNode right;

    public LeafNode(){
        super();
        this.values = new Object[BPlusTree.getmaxKeyNumber()];
        this.left = null;
        this.right = null;
    }

    //二分查找
    @Override
    public T select(V key) {
        if(this.keyNumber <=0)
            return null;
        int low = 0;
        int up = this.keyNumber;
        int middle = (low + up) / 2;
        while(low < up){
            V middleKey = (V) this.keys[middle];
            if(key.compareTo(middleKey) == 0)
                return (T) this.values[middle];
            else if(key.compareTo(middleKey) < 0)
                up = middle;
            else
                low = middle;
            middle = (low + up) / 2;
        }
        return null;
    }


    @Override
    public Node<T, V> insert(T value, V key) {
        //找到插入数据位置
        int i = 0;
        while(i < this.keyNumber){
            if(key.compareTo((V) this.keys[i]) < 0){
                break;
            } else if(key.compareTo((V) this.keys[i]) == 0){
                return null;
            }
            i++;
        }
        //复制数组,完成添加
        Object tempKeys[] = new Object[BPlusTree.getmaxKeyNumber()];
        Object tempValues[] = new Object[BPlusTree.getmaxKeyNumber()];
        System.arraycopy(this.keys, 0, tempKeys, 0, i);
        System.arraycopy(this.values, 0, tempValues, 0, i);
        System.arraycopy(this.keys, i, tempKeys, i + 1, this.keyNumber - i);
        System.arraycopy(this.values, i, tempValues, i + 1, this.keyNumber - i);
        tempKeys[i] = key;
        tempValues[i] = value;
        this.keyNumber++;
            System.out.println("插入完成,当前节点key为:");
            for(int j = 0; j < this.keyNumber; j++)
                System.out.print(tempKeys[j] + " ");
            System.out.println();
        //判断插入值是否超过父节点
        if(i==(this.keyNumber-1)){
            Node node = this;
            V tempkey=(V)tempKeys[this.keyNumber-1];
            while (node.parent != null){
                if(tempkey.compareTo((V)node.parent.keys[node.parent.keyNumber - 1]) > 0){
                    node.parent.keys[node.parent.keyNumber - 1] = tempkey;
                    node = node.parent;
                }
                else {
                    break;
                }
            }
        }
        //保存该节点储存在父节点的key值
        V oldKey = null;
        if(this.keyNumber > 0)
            oldKey = (V) tempKeys[this.keyNumber - 1];
        //判断是否需要拆分
        //如果不需要拆分完成复制后直接返回
        if(this.keyNumber <= BPlusTree.getOrder()){
            System.arraycopy(tempKeys, 0, this.keys, 0, this.keyNumber);
            System.arraycopy(tempValues, 0, this.values, 0, this.keyNumber);
//                System.out.println("叶子节点,插入key: " + key + ",不需要拆分");
            return null;
        }
        //如果需要拆分,则从中间把节点拆分差不多的两部分
        int middle = this.keyNumber / 2;
        //新建叶子节点,作为拆分的右半部分
        LeafNode<T, V> tempNode = new LeafNode<T, V>();
        tempNode.keyNumber = this.keyNumber - middle;
        //如果父节点为空，新建父节点
        if(this.parent == null) {
            NonLeafNode<T, V> tempNonLeafNode = new NonLeafNode<T, V>();
            tempNode.parent = tempNonLeafNode;
            this.parent = tempNonLeafNode;
            oldKey = null;
        }
        tempNode.parent = this.parent;
        System.arraycopy(tempKeys, middle, tempNode.keys, 0, tempNode.keyNumber);
        System.arraycopy(tempValues, middle, tempNode.values, 0, tempNode.keyNumber);
        //让原有叶子节点作为拆分的左半部分
        this.keyNumber = middle;
        this.keys = new Object[BPlusTree.getmaxKeyNumber()];
        this.values = new Object[BPlusTree.getmaxKeyNumber()];
        System.arraycopy(tempKeys, 0, this.keys, 0, middle);
        System.arraycopy(tempValues, 0, this.values, 0, middle);
        //刷新左右叶节点左右指针
        tempNode.right=this.right;
        this.right = tempNode;
        tempNode.left = this;

        //将新节点插入到父节点
        NonLeafNode<T, V> parentNode = (NonLeafNode<T, V>)this.parent;
        return parentNode.insertNode(this, tempNode, oldKey);
    }
}