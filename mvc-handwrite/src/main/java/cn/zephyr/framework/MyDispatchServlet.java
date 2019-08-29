package cn.zephyr.framework;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @ClassName: MyDispatchServlet
 * @Author: laizonghao
 * @Description:
 * @Date: 2019/8/28 17:21
 */
public class MyDispatchServlet extends HttpServlet {
    private MyApplicationContext applicationContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String methodName = applicationContext.getMethodNameByUrl(url);
        Object beanObj = applicationContext.getObjByUrl(url);
        if(StringUtils.isEmpty(methodName) || beanObj == null){
            resp.getWriter().println("404");
            return ;
        }
        try {
            Method declaredMethod = beanObj.getClass().getDeclaredMethod(methodName);
            String result = (String)declaredMethod.invoke(beanObj);
            viewdisplay(result,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() throws ServletException {
        applicationContext = new MyApplicationContext("cn.zephyr");
    }

    // 视图展示
    private void viewdisplay(String pageName, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 获取后缀信息
        String suffix = ".jsp";
        // 页面目录地址
        String prefix = "/";
        req.getRequestDispatcher(prefix + pageName + suffix).forward(req, res);
    }
}
