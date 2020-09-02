package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.HibernateHelper;
import models.Category;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/categories_list"})
public class CategoriesList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
        List<Category> categories = session.createQuery("FROM Category cat").getResultList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(categories);
            response.getWriter().println(json);
        } catch(Exception e) {
            // TODO Handle exceptions
            response.getWriter().println(e.getMessage());
        }
        finally{
            session.close();
        }
    }
}