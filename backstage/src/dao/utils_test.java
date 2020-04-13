package dao;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class utils_test
{
    public static void main (String[] args) throws  Exception
    {
        Map<String,Object> mp = new HashMap<>();
        mp.put("a",1);
        mp.put("b",2);
        Gson gson = new Gson();
        String str = gson.toJson(mp);
        System.out.println(str);
        /*
        test1("Backkom","Well_Down!");
        test2("Backkom","Well_Down!");
        test3("Backkom","Well_Down!","2.2");
        */
       // if(utils.signUp("a","abcdefgh"))
        //    System.out.println("成功");
        //else
          //  System.out.println("失败");
    }
    
    public static void test1(String name, String password){
       /*
        if( utils.login(name,password))
        {
            System.out.println("login 成功!");
        }
        if(utils.isExists(name,password))
        {
            System.out.println("isExists函数 成功!");
        }
        */
    }
    
    public static void test2(String name, String password) {
        ArrayList<User> users =  utils.getInfo("Backkom","Well_Down!");
        for (User user  : users) {
            System.out.println("Name: "+ user.getName());
            System.out.println("Assets: "+ user.getAssets());
            System.out.println("Rates: " + user.getRates());
            System.out.println("Interest: " + user.getInterest());
        }
    }
    public static void test3(String name, String password,String money) {
        BigDecimal bmoney = new BigDecimal(money);
        if ( utils.store(name,password,bmoney)) {
            System.out.println("存取成功！！");
        }
        else {
            System.out.println("存取失败");
        }
        User user = utils.getInfo(name,password).get(0);
        System.out.println(user);
        if(utils.log(name,bmoney)) {
            System.out.println("记录成功！！");
        }
        
        
        
    }
    
}
