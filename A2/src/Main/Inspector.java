package Main;
//import java.lang.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Inspector {

    String formatting = "";

    public void inspect(Object obj, boolean recursive) throws NoSuchMethodException, IllegalAccessException {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) throws NoSuchMethodException, IllegalAccessException {
        // System.out.println(c);

        for(int i =0; i < depth; i++){
            formatting = formatting + "\t";
        }

        System.out.println("########Super Class########");
        recurSuper(c,obj,depth);

        System.out.println("#########Interfaces########");
        recurInter(c,depth);

        System.out.println("########Constructor########");
        for(int i = 0; i < c.getDeclaredConstructors().length; i++){
            //System.out.println(c.getDeclaredConstructors()[i]);
            Constructor con = c.getDeclaredConstructors()[i];
            System.out.println("Name: " +con.getName());
            //System.out.println(con.getDeclaringClass());
            System.out.println("Parameter Types: ");
            for(int j = 0; j < con.getParameterCount(); j++){
                System.out.println("\t" + con.getParameterTypes()[j]);
            }
            System.out.println("Modifiers: " + con.getModifiers());

        }


        System.out.println("########Methods########");
        for(int i = 0; i < c.getDeclaredMethods().length ; i ++){
            Method met = c.getDeclaredMethods()[i];
            //System.out.println(met);
            System.out.println("Name: " + met.getName());
            Class[] metException = met.getExceptionTypes();
            System.out.println("Exceptions: ");
            for (Class excp: metException) {
                System.out.println("\t" + excp);
            }
            Class[] metParam = met.getParameterTypes();
            System.out.println("Parameters: ");
            for(Class param:metParam){
                System.out.println("\t"+param.getName());
            }
            Class metReturn = met.getReturnType();
            System.out.println("Return type: " + metReturn.getName());
            int metModint = met.getModifiers();
            System.out.println("Modifiers: " + Modifier.toString(metModint));


        }

//        Constructor cons = c.getDeclaredConstructor();
//        System.out.println("constructor:" + cons);

        //System.out.println(inter[0]);

        System.out.println("########Fields########");
        for(int i = 0; i < c.getDeclaredFields().length; i++){
            Field f = c.getDeclaredFields()[i];
            f.setAccessible(true);
            //System.out.println(c.getDeclaredFields()[i]);
            Object val = f.get(obj);
            System.out.println("Name:"+f.getName());
            System.out.println("Type: " + f.getType().getName());
            int fval = f.getModifiers();
            System.out.println("Modifier: " + Modifier.toString(fval));
            System.out.println("Value: " + val);
        }
        //Class superC = c.getSuperclass();
        //System.out.println(superC);
//        String testSt = new String("test");
//        Class testcs = testSt.getClass();
//        recurSuper(testcs);
    }

    private void recurSuper(Class c, Object obj, int depth) throws NoSuchMethodException, IllegalAccessException {
        Class superC = c.getSuperclass();
        if(superC != null){
            System.out.println("Super Class of " + c.getName());
            System.out.println(superC);
            //recurSuper(superC, depth+1);
            inspectClass(superC,obj,false,depth+1);;
        }else{

        }
    }

    private void recurInter(Class c, int depth){
        Class[] inter = c.getInterfaces();
        for(int i = 0; i < inter.length; i++){
            System.out.println(inter[i]);
            recurInter(inter[i],depth+1);
        }
    }

}