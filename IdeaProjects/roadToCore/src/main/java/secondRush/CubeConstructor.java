package secondRush;

/**
 * Created by SEWA on 13.05.2017.
 */
public class CubeConstructor {
    int length, bredth, height;

    public int getCubeVolume(){
        return (length*bredth*height); // returns smth after constructor logic
    }
    CubeConstructor() {
        length = 10;
        bredth = 20;
        height = 30;
        System.out.println("prints whenever constructor is initialized");
    }
    // constructors compute different models and never return anything

    CubeConstructor(int l, int b,int h) {
        length = l;
        bredth = b;
        height = h;
        System.out.println("prints whenever constructor is initialized 2");
    }
}
