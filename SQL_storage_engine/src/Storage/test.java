package Storage;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args){
        HashMap<Integer,Student> map = new HashMap<>();
        for(int i = 0; i < 2 ;i++){
            Student x = new Student("test",i);
            map.put(i,x);
        }

        Student root = new Student("root",100);
        root.setNext(map.get(1));
        map.get(1).setNext(map.get(0));
        System.out.println(root.next.next.getId());
    }
}
