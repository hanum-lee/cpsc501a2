package Main;
//import java.lang.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
        System.out.println(c);
        recurSuper(c);

        //Class superC = c.getSuperclass();
        //System.out.println(superC);
    }

    private void recurSuper(Class c){
        Class superC = c.getSuperclass();
        if(superC == null){

        }else{
            System.out.println(superC);
            recurSuper(superC);
        }
    }

    private void recurInter(Class c){

    }

}