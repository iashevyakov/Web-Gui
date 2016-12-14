package ru.itis.inform.servlets;

import ru.itis.inform.dao.NodeDao;
import ru.itis.inform.errors.Err;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.NodeService;
import ru.itis.inform.verifiers.NodeVerify;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteNode extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private NodeService nodeService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        nodeService = ServiceFactory.getInstance().getNodeService();

        String node = req.getParameter("node");

        nodeService.deleteNode(node);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duNode.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/duNode.jsp");

        requestDispatcher.forward(req, resp);
    }
}