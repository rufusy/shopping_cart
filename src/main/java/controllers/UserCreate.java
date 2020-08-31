package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.HibernateHelper;
import models.Person;
import models.User;
import models.UserGroup;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/users/create"})
public class UserCreate extends HttpServlet {
    private UserGroup userGroup;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean groupSaved = false;
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String group = request.getParameter("user-group");
        String password = "secret"; //TODO generate random password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Session session = HibernateHelper.getSessionFactory().openSession();
        this.userGroup = (UserGroup) session.createQuery("FROM UserGroup UG WHERE UG.id = :user_group_id")
                .setParameter("user_group_id",Integer.parseInt(group))
                .getSingleResult();
        session.close();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.userGroup);
            response.getWriter().println(json);
        } catch(Exception e) {
            e.printStackTrace();
        }
//        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
//        Transaction tx = session.getTransaction();
//        tx.begin();


//        User user = new User();
//        user.setPerson(new Person());
//        user.getPerson().setFirstName(firstName);
//        user.getPerson().setLastName(lastName);
//        user.getPerson().setEmail(email);
//        user.getPerson().setTelephone(telephone);
//        user.getPerson().setPassword(hashedPassword);

//        String hql = "FROM UserGroup UG WHERE UG.user_group_id = :user_group_id";
//        Query query = session.createQuery(hql);
//        query.setParameter("user_group_id",group);
//        UserGroup userGroup = (UserGroup)query.getSingleResult();


//        user.setUserGroup(userGroup);
//
//        session.save(user);
//        tx.commit();
//        if(user != null)
//            groupSaved = true;
//        session.close();
//
//        // create `ObjectMapper` instance
//        ObjectMapper mapper = new ObjectMapper();
//        // create a JSON object
//        ObjectNode json = mapper.createObjectNode();
//
//        if(groupSaved)
//            json.put("saved", true);
//        else
//            json.put("saved", false);
//
//        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
//        response.getWriter().println(data);
    }

    /**
     * Get all user groups available
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().openSession();
        List<UserGroup> userGroups= session.createQuery("FROM UserGroup s").getResultList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userGroups);
            response.getWriter().println(json);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
