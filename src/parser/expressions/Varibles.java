package parser.expressions;

import java.util.HashMap;
import java.util.Map;

public class Varibles {
    
    private static Map<String,Double> varibles;
    
    static{
        varibles = new HashMap<>();
    }
    
    public static boolean isExist(String varibleName){
        return varibles.containsKey(varibleName);
    }
    
    public static double get(String varibleName){
        if(!isExist(varibleName)) return 0;
        return varibles.get(varibleName);
    }
    
    public static void set(String varibleName, double value){
        varibles.put(varibleName, value);
    }
}
