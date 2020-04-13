package servlet;

import com.google.gson.Gson;
import dao.User;
import dao.utils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/store")
public class store extends HttpServlet
{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        String upasword = req.getParameterValues("password")[0];
        String uname = req.getParameterValues("name")[0];
        BigDecimal store_money = new BigDecimal( req.getParameterValues("money")[0]);
    
        System.out.println(store_money);
        Map<String,Object> mp = new HashMap<>();
        
        upasword = utils.Base64Encode(upasword);
        
        if (utils.store(uname,upasword,store_money) ) {
            
            User u =  utils.getInfo(uname,upasword).get(0);
            
            PrintWriter pw = resp.getWriter();
            String str = "";
            str += "{\"assets\":" + u.getAssets();
            str += ",\"rates\":" + u.getRates();
            str += ",\"interest\":" + u.getInterest()+"}";
            pw.println(str);
            System.out.println("json :" + str);
            System.out.println(uname + " 存取成功！！");
    
            pw.flush();
            pw.close();
        } else {
            System.out.println(uname + "存取失败！！");
        }
        if (utils.log(uname,store_money)) {
            System.out.println(uname + "记录成功");
        } else {
            System.out.println(uname + "记录失败");
        }
        
        
    }
    
    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doGet(req,resp);
    }
}
