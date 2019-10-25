package Main;
//import java.lang.*;

import java.lang.reflect.*;
import java.util.Arrays;

public class Inspector {

    public void inspect(Object obj, boolean recursive) throws NoSuchMethodException, IllegalAccessException {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) throws NoSuchMethodException, IllegalAccessException {
        // System.out.println(c);

        if(c.isArray()){
            Object[] tempArr = new Object[Array.getLength(obj)];
            for(int i = 0; i < tempArr.length; i++ ){
                Object objInArray = Array.get(obj,i);
                if(objInArray != null && objInArray.getClass().isArray()){
                    Object[] tempArr2 = new Object[Array.getLength(objInArray)];
                    String arrOut = "[";
                    for(int j = 0; j < tempArr2.length; j++){
                        //System.out.println(Array.get(objInArray,j));
                        if(j == tempArr2.length -1){
                            arrOut = arrOut + Array.get(objInArray,j);
                        }else{
                            arrOut = arrOut + Array.get(objInArray,j) + ", ";
                        }
                    }
                    arrOut = arrOut + "]";
                    System.out.println(arrOut);
                    arrOut = "[";
                }else{
                    System.out.println(Array.get(obj,i));
                }

            }

        }
        String formatting = "";
        for(int i =0; i < depth; i++){
            formatting = formatting + "\t";
        }

        String innerFormat = formatting + "   ";
        System.out.println(formatting + "########Super Class########");
        recurSuper(c,obj,depth);

        System.out.println(formatting + "#########Interfaces########");
        recurInter(c,obj,depth);

        System.out.println(formatting + "########Constructor########");
        for(int i = 0; i < c.getDeclaredConstructors().length; i++){
            //System.out.println(c.getDeclaredConstructors()[i]);
            Constructor con = c.getDeclaredConstructors()[i];
            System.out.println(innerFormat + "Name: " +con.getName());
            //System.out.println(con.getDeclaringClass());
            System.out.println(innerFormat + "Parameter Types: ");
            for(int j = 0; j < con.getParameterCount(); j++){
                System.out.println(innerFormat + "   " + con.getParameterTypes()[j]);
            }
            System.out.println(innerFormat + "Modifiers: " + con.getModifiers());

        }


        System.out.println(formatting + "########Methods########");
        for(int i = 0; i < c.getDeclaredMethods().length ; i ++){
            Method met = c.getDeclaredMethods()[i];
            //System.out.println(met);
            System.out.println(innerFormat + "Name: " + met.getName());
            Class[] metException = met.getExceptionTypes();
            System.out.println(innerFormat + "Exceptions: ");
            for (Class excp: metException) {
                System.out.println(innerFormat + "   " + excp);
            }
            Class[] metParam = met.getParameterTypes();
            System.out.println(innerFormat + "Parameters: ");
            for(Class param:metParam){
                System.out.println(innerFormat + "   " + param.getName());
            }
            Class metReturn = met.getReturnType();
            System.out.println(innerFormat + "Return type: " + metReturn.getName());
            int metModint = met.getModifiers();
            System.out.println(innerFormat + "Modifiers: " + Modifier.toString(metModint));


        }

//        Constructor cons = c.getDeclaredConstructor();
//        System.out.println("constructor:" + cons);

        //System.out.println(inter[0]);

        System.out.println(formatting + "########Fields########");
        for(int i = 0; i < c.getDeclaredFields().length; i++){
            Field f = c.getDeclaredFields()[i];
            f.setAccessible(true);
            //System.out.println(c.getDeclaredFields()[i]);
            Object val = f.get(obj);
            System.out.println(innerFormat + "Name:"+f.getName());

/*            if(f.getType().isArray()){
                System.out.println(innerFormat + "Type: " + f.getType().getComponentType());
            }else{

            }*/
            System.out.println(innerFormat + "Type: " + f.getType().getName());
            int fval = f.getModifiers();
            System.out.println(innerFormat + "Modifier: " + Modifier.toString(fval));

            if(f.getType().isArray()){
                Object[] temparr = new Object[Array.getLength(val)];
                System.out.println(innerFormat + "Array Value:");
                for(int j = 0; j < temparr.length ; j++){
                    System.out.println(innerFormat + "  " + Array.get(val,j));
                }
            }else{
                System.out.println(innerFormat + "Value: " + val);
            }


        }
        //Class superC = c.getSuperclass();
        //System.out.println(superC);
//        String testSt = new String("test");
//        Class testcs = testSt.getClass();
//        recurSuper(testcs);
    }

    private void recurSuper(Class c, Object obj, int depth) throws NoSuchMethodException, IllegalAccessException {
        String formatting = "";
        for(int i =0; i < depth; i++){
            formatting = formatting + "\t";
        }
        String innerFormat = formatting + "   ";

        Class superC = c.getSuperclass();
        if(superC != null){
            //System.out.println("Super Class of " + c.getName());
            System.out.println(innerFormat + "Name: " + superC.getName());
            //recurSuper(superC, depth+1);
            inspectClass(superC,obj,false,depth+1);;
        }
    }

    private void recurInter(Class c, Object obj, int depth) throws NoSuchMethodException, IllegalAccessException {
        String formatting = "";
        for(int i =0; i < depth; i++){
            formatting = formatting + "\t";
        }
        String innerFormat = formatting + "   ";

        Class[] inter = c.getInterfaces();
        for(int i = 0; i < inter.length; i++){
            System.out.println(innerFormat + inter[i].getName());
            //recurInter(inter[i],depth+1);
            inspectClass(inter[i],obj, false, depth+1);
        }
    }

}