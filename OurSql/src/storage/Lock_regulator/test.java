package Lock_regulator;

import java.io.File;
import Storage.XMLUtils;

public class test {
    public static void main(String[] args) {
        File file = new File("data/");
        File[] files = file.listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
                //System.out.println("文件夹:" + file2.getPath().substring(4));
                File datafile = new File(file2.getPath()+XMLUtils.getFileName(file2.getPath())+".txt");
                System.out.println(datafile.getPath());
            } else {
                System.out.println("文件:" + file2.getPath());
            }
        }
    }
}
