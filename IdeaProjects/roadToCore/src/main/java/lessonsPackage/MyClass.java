package lessonsPackage;


/**
 * Created by SEWA on 11.05.2017.
 */
public class MyClass {
    public static void main(String[] args){
        Persons person = new Persons();
        person.id = 1;
        person.name = "Vasya";
        person.setId(2);
        person.setName("Vovan");

        System.out.println(Persons.getNoOfPeople());

        Persons Tom = new Persons();
        Persons Jojo = new Persons();

        System.out.println(Persons.getNoOfPeople());

    }
}
