package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.HibernateHelper;
import models.User;
import models.UserGroup;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/users/list"})
public class UsersList extends HttpServlet {
    /**
     * Get all users available
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Session session = HibernateHelper.getSessionFactory().openSession();
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();

        List<User> users = session.createQuery("FROM User s").getResultList();
        //session.close();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
            response.getWriter().println(json);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

