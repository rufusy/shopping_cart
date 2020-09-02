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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = {"/user_create"})
public class UserCreate extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String group = request.getParameter("user-group");
        String password = "secret"; //TODO generate random password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Session session = HibernateHelper.getSessionFactory().openSession();
//        UserGroup userGroup = (UserGroup) session.createQuery("FROM UserGroup UG WHERE UG.id = :user_group_id")
//                .setParameter("user_group_id",Integer.parseInt(group))
//                .getSingleResult();
        UserGroup userGroup = session.load(UserGroup.class, Integer.parseInt(group));

        Transaction tx = session.getTransaction();
        tx.begin();
        User user = new User();
        user.setPerson(new Person());
        user.getPerson().setFirstName(firstName);
        user.getPerson().setLastName(lastName);
        user.getPerson().setEmail(email);
        user.getPerson().setTelephone(telephone);
        user.getPerson().setPassword(hashedPassword);
        user.getPerson().setDateAdded(new Date());
        user.getPerson().setStatus(true);
        user.setUserGroup(userGroup);
        session.save(user);
        tx.commit();

        // create `ObjectMapper` instance
        ObjectMapper mapper = new ObjectMapper();
        // create a JSON object
        ObjectNode json = mapper.createObjectNode();
        json.put("saved", true);
        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

}
