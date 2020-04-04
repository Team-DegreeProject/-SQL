package table.type;

public class Int {
    private int scale=-1;
    private int data;
    public Int(int data){
        this.data=data;
    }
    public Int(int data,int scale) throws Exception {
        this.data=data;
        this.scale=scale;
    }
    public String intToString(){
        if(scale==-1){
            return Integer.toString(data);
        }else{
            String str=Integer.toString(data);
            if(scale<=str.length()){
                return str;
            }else{
                int zeroNum=scale-str.length();
                String zeros="";
                for(int i=0;i<zeroNum;i++){
                    zeros=zeros+"0";
                }
                return zeros+str;
            }
        }
    }

}
