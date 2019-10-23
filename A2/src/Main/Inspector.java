package Main;
//import java.lang.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Inspector {

    public void inspect(Object obj, boolean recursive) throws NoSuchMethodException, IllegalAccessException {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) throws NoSuchMethodException, IllegalAccessException {
        System.out.println(c);
        recurSuper(c);
        for(int i = 0; i < c.getDeclaredConstructors().length; i++){
            //System.out.println(c.getDeclaredConstructors()[i]);
            Constructor con = c.getDeclaredConstructors()[i];
            System.out.println(con.getName());
            System.out.println(con.getDeclaringClass());
            for(int j = 0; j < con.getParameterCount(); j++){
                System.out.println(con.getParameterTypes()[j]);
            }
            System.out.println(con.getModifiers());

        }



//        Constructor cons = c.getDeclaredConstructor();
//        System.out.println("constructor:" + cons);
        Class[] inter = c.getInterfaces();
        System.out.println(inter[0]);

        for(int i = 0; i < c.getDeclaredFields().length; i++){
            Field f = c.getDeclaredFields()[i];
            f.setAccessible(true);
            //System.out.println(c.getDeclaredFields()[i]);
            System.out.println(f.get(obj));
        }
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