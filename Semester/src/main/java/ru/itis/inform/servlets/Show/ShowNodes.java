package ru.itis.inform.servlets.Show;

import ru.itis.inform.dao.interfaces.NodeDao;
import ru.itis.inform.factories.DaoFactory;
import ru.itis.inform.models.Node;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;


public class ShowNodes extends HttpServlet {

    private HttpSession session;

    private RequestDispatcher requestDispatcher;

    private LinkedList<Node> nodes;

    private NodeDao nodeDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        session = req.getSession();

        req.setAttribute("current_user", session.getAttribute("current_user"));

        nodeDao = DaoFactory.getInstance().getNodeDao();

        nodes = nodeDao.findAll();

        req.setAttribute("nodes",nodes);

        requestDispatcher = getServletContext().getRequestDispatcher("/nodes.jsp");

        requestDispatcher.forward(req, resp);

    }


}