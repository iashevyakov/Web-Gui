package ru.itis.inform.servlets.Add;

import ru.itis.inform.factories.ServiceFactory;
import ru.itis.inform.services.interfaces.NodeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class AddNode extends HttpServlet {

    private RequestDispatcher requestDispatcher;

    private HttpSession session;

    private NodeService nodeService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String unit = req.getParameter("unit");

        String node = req.getParameter("node");

        String inventor_name = req.getParameter("inventor_name");

        String inventor_country = req.getParameter("inventor_country");

        String date = req.getParameter("date");

        nodeService = ServiceFactory.getInstance().getNodeService();

        nodeService.addNode(unit,node,inventor_name,inventor_country,date);

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addNode.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        requestDispatcher = getServletContext().getRequestDispatcher("/addNode.jsp");

        requestDispatcher.forward(req, resp);
    }
}