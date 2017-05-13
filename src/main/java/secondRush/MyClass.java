package secondRush;

/**
 * Created by SEWA on 13.05.2017.
 */
public class MyClass {
    public static void main(String[] args) {
        CubeConstructor cube1 = new CubeConstructor();
        System.out.println(cube1.getCubeVolume());

        CubeConstructor cube2 = new CubeConstructor(2,3,4);
        System.out.println(cube2.getCubeVolume());
    }

}
