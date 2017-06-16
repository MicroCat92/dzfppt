package sajt.dzfppt.util;

import com.google.gson.Gson;
import com.google.gson.JsonNull;

public class JsonUtil  
{  
      
    private static Gson gson=new Gson();  
  
  
    /** 
     * @MethodName : toJson 
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求 
     * @param src 
     *            :将要被转化的对象 
     * @return :转化后的JSON串 
     */  
    public static String toJson(Object src) {  
        return gson.toJson(src);  
    }  
} 
