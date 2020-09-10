package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.UserBean;
import models.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/login"})
public class LoginController extends HttpServlet {

    @EJB
    private UserBean userBean;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String name =(String)session.getAttribute("name");
        String group =(String)session.getAttribute("group");
        String email = (String)session.getAttribute("email");

        boolean loggedIn = false;

        if(name != null && group != null && email != null) {
            loggedIn = true;
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("name", name);
        json.put("group", group);
        json.put("email", email);
        json.put("loggedIn", loggedIn);
        String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        response.getWriter().println(data);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String authenticationMsg = "";
        boolean authenticated = true;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        try{
            user = this.userBean.authenticate(email, password);
//            BeanUtils.populate(user, request.getParameterMap());
//            user = this.userBean.authenticate(user);
            authenticationMsg = "Login successful";
        }catch (Exception ex){
            ex.printStackTrace();
            authenticationMsg = ex.getMessage();
            authenticated = false;
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            if(authenticated) {
                HttpSession session = request.getSession(true);
                session.setAttribute("name", user.getPerson().getFirstName() +" "+user.getPerson().getLastName());
                session.setAttribute("group", user.getUserGroup().getName());
                session.setAttribute("email", user.getPerson().getEmail());
                json.put("authenticated", true);
            }
            else
                json.put("authenticated", false);

            json.put("authMsg", authenticationMsg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }
}
