package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;
import next.utils.UserSessionUtils;
import next.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebServlet("/user/list")
public class ListUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ListUserServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserSessionUtils.getUserFromSession(req.getSession());

        if (!UserSessionUtils.isLogined(req.getSession())) {
            log.error("user is NULL");
            Utils.getInstance().goLogin(resp);
            return;
        }

        req.setAttribute("users", DataBase.findAll());
        req.setAttribute("myId", user.getUserId());
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
