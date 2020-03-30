package Storage;

public class XMLUtils {

    public static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static String convertToLetter(String sentence){
        String regex = "[^(A-Za-z)]";
        return sentence.replaceAll(regex,"");
    }
}
