package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.HibernateHelper;
import models.Category;
import models.Common;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/category_update"})
public class CategoryUpdate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String metatitle = request.getParameter("metatitle");
        String metakeyword = request.getParameter("metakeyword");
        String metadescription = request.getParameter("metadescription");
        String parent = request.getParameter("parent-category");
        String catId = request.getParameter("cat-id");

        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        //if(!this.isNullOrEmpty(parent))
        Category parentCategory = session.load(Category.class, Integer.parseInt(parent));
        Category category = session.load(Category.class, Integer.parseInt(catId));

        category.setName(name);
        category.setDescription(description);
        if (metatitle != "") category.setMetaTitle(metatitle);
        if (metakeyword != "") category.setMetaKeyword(metakeyword);
        if (metadescription != "") category.setMetaDescription(metadescription);
        category.setParentCategory(parentCategory);

        category.setCommon(new Common());
        category.getCommon().setStatus(true);
        category.getCommon().setDateAdded(new Date());
        category.getCommon().setDateModified(new Date());

        session.update(category);
        tx.commit();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("updated", true);
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

