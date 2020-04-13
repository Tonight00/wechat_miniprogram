package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.utils;
import java.io.PrintWriter;

@WebServlet("/login")
public class login extends HttpServlet
{
    @Override
    protected void doPut (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String upassword = request.getParameterValues("password")[0];
        String uname = request.getParameterValues("name")[0];
        String id = request.getParameterValues("id")[0];
        String tel = request.getParameterValues("tel")[0];
        String epassword = utils.Base64Encode(upassword);
        
        
        System.out.println("login _ 用户名; " + uname);
        System.out.println("login _ 密码: " + upassword);
        System.out.println("login_身份证号"+ id);
        System.out.println("login_电话号码" + tel);
        System.out.println("login _ Base64加密; " + epassword);
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();//创建输出流
        
        if( utils.login(uname,epassword,id,tel) ) {
            System.out.println(" 注册成功！！");
            printWriter.print(true);
        }
        else {
            System.out.println(" 注册失败");
            printWriter.print(false);
        }
        printWriter.flush();
        printWriter.close();
    }
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPut(req,resp);
    }
}
