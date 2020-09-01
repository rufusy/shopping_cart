package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.HibernateHelper;
import models.UserGroup;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user_group/create"})
public class UserGroupCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean groupSaved = false;
        String groupName = request.getParameter("group-name");
        String permissions = request.getParameter("permissions");

        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        UserGroup userGroup  = new UserGroup();
        userGroup.setName(groupName);
        userGroup.setPermission(permissions);
        session.save(userGroup);
        tx.commit();
        if(userGroup != null)
            groupSaved = true;
        //session.close();

        // create `ObjectMapper` instance
        ObjectMapper mapper = new ObjectMapper();
        // create a JSON object
        ObjectNode json = mapper.createObjectNode();

        if(groupSaved)
            json.put("saved", true);
        else
            json.put("saved", false);

        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }
}
