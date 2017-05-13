package lessonsPackage;

import java.util.Scanner;

/**
 * Created by SEWA on 11.05.2017.
 */
// && = AND  || = OR
public class lesson1 {


    public static void main(String[] args){

        Hello backMessage = new Hello();   //static method belongs to class(Hello), nonstatic belongs to instance(backMessage)
        Hello.doSomething("Static method");
        backMessage.doSomethingElse("nonStatic method");

        scannerObjectForInput();
        increment();
        arrayz();
        stringz();
    }
    public static void increment(){
        int x = 10;
        System.out.println("x = "+ x++);//postincrement
        System.out.println("x = "+ x);
        System.out.println("x = "+ ++x);//preincrement

    }
    public static void scannerObjectForInput(){
        Scanner scan = new Scanner(System.in);//объект для обработки ввода
        System.out.println("output");
        int user_input_number = scan.nextInt();//принимает ввод юзера
        System.out.println(user_input_number);
        switch (user_input_number)
        {
            case 1:
                System.out.println("1");
                break; // mandatory break for switch cases

            case 2:
                System.out.println("2");
                break;

            default:
                System.out.println("default");
                break;
        }
    }
    public static void arrayz (){
        int[] myIntArray = {100,2,3};
        for (int element : myIntArray){
            System.out.println(element);
        }
    }
    public static void stringz(){
        String txt = "neveroyatno";
        System.out.println(txt.replace('e','Z'));
    }
}
