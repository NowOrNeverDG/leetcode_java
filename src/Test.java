import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Test {

        public static void main(String []args){
            Scanner sr=new Scanner(System.in);
            while(sr.hasNext()){
                String str=sr.nextLine();
                for(int i=0;i<4;i++){
                    System.out.println(getNum(str)[i]);
                }
            }
        }

        public static int [] getNum(String str){
            //4种情况的一个数组
            int [] num=new int[4];
            char []ch=str.toCharArray();
            for(int i=0;i<ch.length;i++){
                //判断英文字母
                if((ch[i]>='a'&&ch[i]<='z')||(ch[i]>='A'&&ch[i]<='Z')){
                    num[0]++;
                    //判断空格
                }else if(ch[i]==' '){
                    num[1]++;
                    //判断数字字符
                }else if(ch[i]>='0'&&ch[i]<='9'){
                    num[2]++;
                    //判断其他字符
                }else{
                    num[3]++;
                }
            }
            return num;
        }
    }
