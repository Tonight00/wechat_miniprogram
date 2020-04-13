package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import dao.User;
import dao.utils;

@WebServlet("/query")
public class query extends HttpServlet
{
    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        String uname = req.getParameterValues("name")[0];
        User user = utils.getInfo(uname,
                utils.Base64Encode(req.getParameterValues("password")[0]) )
                .get(0);
        Map<String,Object> mp = new HashMap<>();
        
        {
            mp.put("name",user.getName());
            mp.put("assets",user.getAssets());
            mp.put("rates",user.getRates());
            mp.put("interest",user.getInterest());
        }
        
        System.out.println(mp);
        
         //Gson gson = new Gson();
        //String jsonStr = gson.toJson(mp);
        //System.out.println(jsonStr);
        PrintWriter pw = resp.getWriter();
        String str = "";
        str += "{\"assets\":" + user.getAssets();
        str += ",\"rates\":" + user.getRates();
        str += ",\"interest\":" + user.getInterest()+"}";
        pw.print(str);
    
    }
    
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPut(req, resp);
    }
}
