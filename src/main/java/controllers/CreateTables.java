package controllers;

import database.HibernateHelper;
import models.UserGroup;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/create-tables"})
public class CreateTables extends HttpServlet {
    /**
     * Create tables
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HibernateHelper.getSessionFactory();
//        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
//        Transaction tx = session.getTransaction();
//        tx.begin();
//        UserGroup userGroup = new UserGroup();
//        userGroup.setName("Administrator");
//        userGroup.setPermission("");
//        session.save(userGroup);
//        tx.commit();
    }
}
