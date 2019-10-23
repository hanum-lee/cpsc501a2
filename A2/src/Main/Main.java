package Main;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        System.out.println("Tests");
        Object testObj = new ClassA();
        Class objClass = testObj.getClass();
        System.out.println(objClass);
        ClassA classA = new ClassA();
        Inspector inspec = new Inspector();
        inspec.inspect(new ClassB(), false);


    }
}
