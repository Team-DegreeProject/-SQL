class NonLeafNode<T, V extends Comparable<V>> extends Node<T, V> {
    //子节点
    protected Node<T, V>[] childs;
    public NonLeafNode() {
        super();
        this.childs = new Node[BPlusTree.getmaxKeyNumber()];
    }
    @Override
    public T select(V key) {
        int i = 0;
        while(i < this.keyNumber){
            if(key.compareTo((V) this.keys[i]) <= 0)
                break;
            i++;
        }
        if(this.keyNumber == i)
            return null;
        return this.childs[i].select(key);
    }

    @Override
    public Node<T, V> insert(T value, V key) {
        System.out.println("当前节点key为:");
        for(int j = 0; j < this.keyNumber; j++)
            System.out.print(this.keys[j] + " ");
            System.out.println();
        if(key.compareTo((V) this.keys[this.keyNumber - 1]) == 0) {
            return null;
        }
        int i = 0;
        while(i < this.keyNumber){
            if(key.compareTo((V) this.keys[i]) < 0)
                break;
            i++;
        }
        if(key.compareTo((V) this.keys[this.keyNumber - 1]) > 0) {
            i--;
        }

        return this.childs[i].insert(value, key);
    }


    public Node<T, V> insertNode(Node<T, V> node1, Node<T, V> node2, V key){
        V oldKey = null;
        if(this.keyNumber > 0)
            oldKey = (V) this.keys[this.keyNumber - 1];
        //如果非叶节点为空,直接插入两个节点
        if(key == null || this.keyNumber <= 0){
//                System.out.println("非叶子节点,插入key: " + node1.keys[node1.keyNumber - 1] + " " + node2.keys[node2.keyNumber - 1] + "直接插入");
            this.keys[0] = node1.keys[node1.keyNumber - 1];
            this.keys[1] = node2.keys[node2.keyNumber - 1];
            this.childs[0] = node1;
            this.childs[1] = node2;
            this.keyNumber += 2;
            return this;
        }
        //如果非叶节点不为空,则应该先寻找原有节点的位置,然后将新的节点插入到原有节点中
        int i = 0;
        while(key.compareTo((V)this.keys[i]) != 0){
            i++;
        }
        //插入左节点
        this.keys[i] = node1.keys[node1.keyNumber - 1];
        this.childs[i] = node1;
        Object tempKeys[] = new Object[BPlusTree.getmaxKeyNumber()];
        Object tempChilds[] = new Node[BPlusTree.getmaxKeyNumber()];
        System.arraycopy(this.keys, 0, tempKeys, 0, i + 1);
        System.arraycopy(this.childs, 0, tempChilds, 0, i + 1);
        System.arraycopy(this.keys, i + 1, tempKeys, i + 2, this.keyNumber - i - 1);
        System.arraycopy(this.childs, i + 1, tempChilds, i + 2, this.keyNumber - i - 1);
        //插入右节点
        tempKeys[i + 1] = node2.keys[node2.keyNumber - 1];
        tempChilds[i + 1] = node2;
        this.keyNumber++;
        //判断是否需要拆分
        //如果不需要拆分,把数组复制回去,直接返回
        if(this.keyNumber <= BPlusTree.getOrder()){
            System.arraycopy(tempKeys, 0, this.keys, 0, this.keyNumber);
            System.arraycopy(tempChilds, 0, this.childs, 0, this.keyNumber);
            return null;
        }
        //如果需要拆分,从中间拆开
        int middle = this.keyNumber / 2;
        //新建非叶子节点,作为拆分的右半部分
        NonLeafNode<T, V> tempNode = new NonLeafNode<T, V>();
        //非叶节点拆分后应该将其子节点的父节点指针更新为正确的指针
        tempNode.keyNumber = this.keyNumber - middle;
        //如果父节点为空,则新建一个非叶子节点作为父节点,并且让拆分成功的两个非叶子节点的指针指向父节点
        if(this.parent == null) {
            NonLeafNode<T, V> tempNonLeafNode = new NonLeafNode<>();
            tempNode.parent = tempNonLeafNode;
            this.parent = tempNonLeafNode;
            oldKey = null;
        }
        tempNode.parent = this.parent;
        System.arraycopy(tempKeys, middle, tempNode.keys, 0, tempNode.keyNumber);
        System.arraycopy(tempChilds, middle, tempNode.childs, 0, tempNode.keyNumber);
        for(int j = 0; j < tempNode.keyNumber; j++){
            tempNode.childs[j].parent = tempNode;
        }
        //让原有非叶子节点作为左边节点
        this.keyNumber = middle;
        this.keys = new Object[BPlusTree.getmaxKeyNumber()];
        this.childs = new Node[BPlusTree.getmaxKeyNumber()];
        System.arraycopy(tempKeys, 0, this.keys, 0, middle);
        System.arraycopy(tempChilds, 0, this.childs, 0, middle);

        //叶子节点拆分成功后,需要把新生成的节点插入父节点
        NonLeafNode<T, V> parentNode = (NonLeafNode<T, V>)this.parent;
        return parentNode.insertNode(this, tempNode, oldKey);
    }

}