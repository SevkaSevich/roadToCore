package secondRush;

/**
 * Created by SEWA on 13.05.2017.
 */
public class MethodOverloading {
    public static void main(String[] args) {
        System.out.println(Add(1,33));
        System.out.println(Add(1.22,33.45));
        System.out.println(Add("gl ","hf"));
    }
    public static int Add (int a,int b){
        return (a+b);
    }

    public static double Add (double a,double b){
        return (a+b);
    }

    public static String Add (String a,String b){
        return (a+b);
    }

}
