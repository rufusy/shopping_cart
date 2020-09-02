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


@WebServlet(urlPatterns = {"/category_show"})
public class CategoryShow extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Session session = HibernateHelper.getSessionFactory().openSession();
        Category category = session.load(Category.class, Integer.parseInt(id));

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(category);
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