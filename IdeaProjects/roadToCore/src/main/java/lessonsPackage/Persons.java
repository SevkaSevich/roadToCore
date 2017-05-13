package lessonsPackage;

/**
 * Created by SEWA on 11.05.2017.
 */
public class Persons {
    int id;
    String name;
    static int NoOfPeople = 0; //static variable can be shared by instances

    Persons (){
        NoOfPeople++;
    }
    public static int getNoOfPeople(){
        return (NoOfPeople);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

}
