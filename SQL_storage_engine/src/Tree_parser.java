import com.sun.source.tree.Tree;
import java.util.Arrays;
import java.util.List;

public class Tree_parser {
    Tree BplusTree;
    String[] nodePool;
    public Tree_parser(Tree T){
        this.BplusTree = T;
    }

    public void loadTree(Tree tree){

    }

    public String[] loadNode(String id){
        List<String> list = Arrays.asList(nodePool);
        boolean flag = Arrays.asList(nodePool).contains(id);
        if(flag){
            return nodePool;
        }
        return null;
    }

}
