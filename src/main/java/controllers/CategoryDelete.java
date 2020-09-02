package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.HibernateHelper;
import models.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/category_delete"})
public class CategoryDelete extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Category category = session.load(Category.class, Integer.parseInt(id));
        session.remove(category);
        tx.commit();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("deleted", true);
        try {
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        } catch (Exception e) {
            // TODO Handle exceptions
            response.getWriter().println(e.getMessage());
        } finally {
            session.close();
        }
    }
}