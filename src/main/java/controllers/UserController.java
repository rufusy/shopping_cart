package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.UserBean;
import models.User;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/users"})
public class UserController extends HttpServlet {

    @EJB
    private UserBean userBean;

    /**
     * Get users or user by id
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String msg="";
        String data = "";
        boolean userFound = true;
        boolean usersFound = true;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNodes = mapper.createObjectNode();

        if(StringUtils.isBlank(id)){
            List<User> users;
            ObjectNode usersNode = mapper.createObjectNode();
            try{
                users = this.userBean.list();
                usersNode.put("usersFound", true);
//                ObjectNode usersDetailsNode = usersNode.putObject("users");

                ObjectNode usersDetailsNode = mapper.createObjectNode();
                ArrayNode usersArrayNode = mapper.createArrayNode();
                for(User user : users){
                    usersDetailsNode.put("firstName", user.getPerson().getFirstName());
                    usersDetailsNode.put("lastName", user.getPerson().getLastName());
                    usersDetailsNode.put("email", user.getPerson().getEmail());
                    usersDetailsNode.put("telephone", user.getPerson().getTelephone());
                    usersArrayNode.add(usersDetailsNode);
                }

           }catch(Exception ex){
                msg = ex.getMessage();
                usersFound = false;
                ex.printStackTrace();
           }
           finally {
               if(!usersFound){
                   jsonNodes.put("msg", msg);
                   jsonNodes.put("usersFound", false);
                   data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodes);
               }else {
                   data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usersNode);
               }
               response.getWriter().println(data);
           }
       }
       else{
            User user = new User();
            try{
               user = this.userBean.show(id);
           }catch(Exception ex){
               msg = ex.getMessage();
               userFound = false;
               ex.printStackTrace();
           }
           finally {
               if(!userFound) {
                   jsonNodes.put("msg", msg);
                   jsonNodes.put("userFound", false);
                   data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodes);
               }
               else {
                   ObjectNode userNode = mapper.createObjectNode();
                   userNode.put("userFound", true);
                   ObjectNode userDetailsNode = userNode.putObject("user");
                   userDetailsNode.put("firstName", user.getPerson().getFirstName());
                   userDetailsNode.put("lastName", user.getPerson().getLastName());
                   userDetailsNode.put("email", user.getPerson().getEmail());
                   userDetailsNode.put("telephone", user.getPerson().getTelephone());
                   data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userNode);
               }
               response.getWriter().println(data);
           }
       }
    }

    /**
     * Create new user
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String msg = "";
        boolean userCreated = false;

        try{
            this.userBean.create(this.getRequestParams(request));
            msg = "User created successfully";
            userCreated = true;
        }catch (Exception ex) {
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNodes = mapper.createObjectNode();
            jsonNodes.put("msg",msg);
            jsonNodes.put("userCreated", userCreated);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodes);
            response.getWriter().println(data);
        }
    }

    /**
     * Update user
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String msg = "";
        boolean userUpdated = false;
        try{
            this.userBean.update(this.getRequestParams(request));
            msg = "User Updated successfully";
            userUpdated = true;
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNodes = mapper.createObjectNode();
            jsonNodes.put("msg",msg);
            jsonNodes.put("userUpdated", userUpdated);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodes);
            response.getWriter().println(data);
        }
    }

    /**
     * Delete user
     * @param request
     * @param response
     * @throws IOException
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String msg = "";
        boolean userDeleted = false;
        try{
            this.userBean.delete(id);
            msg = "User deleted successfully";
            userDeleted = true;
        }catch (Exception ex){
            msg = ex.getMessage();
            ex.printStackTrace();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNodes = mapper.createObjectNode();
            jsonNodes.put("msg",msg);
            jsonNodes.put("userDeleted", userDeleted);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNodes);
            response.getWriter().println(data);
        }
    }

    /**
     * Get params from request
     * @param request
     * @return
     */
    private HashMap<String, String> getRequestParams(HttpServletRequest request){
        HashMap<String, String> userDetails = new HashMap<>();
        Enumeration en = request.getParameterNames();
        while(en.hasMoreElements()){
            Object obj = en.nextElement();
            String paramName = (String)obj;
            String paramValue = request.getParameter(paramName);
            userDetails.put(paramName, paramValue);
        }
        return userDetails;
    }
}
