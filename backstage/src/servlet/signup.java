package servlet;

import dao.utils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sign")
public class signup extends HttpServlet
{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String uname = req.getParameterValues("name")[0];
        String upassword = req.getParameterValues("password")[0];
        String epassword = utils.Base64Encode(upassword);
        PrintWriter pw = resp.getWriter();
        boolean ret;
        pw.println(ret = utils.signUp(uname,epassword));
        if (ret) {
            System.out.println(uname + "登录成功 ！！");
        }
        else {
            System.out.println(uname + "登录失败 ！！");
        }
        pw.flush();
        pw.close();
    }
    
    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doGet(req,resp);
    }
}
