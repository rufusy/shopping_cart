package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        if(StringUtils.isBlank(id)){
            List<User> users = new ArrayList<>();
            try{
                users = this.userBean.list();
                msg = "Users fetched successfully";
           }catch(Exception ex){
                msg = ex.getMessage();
                ex.printStackTrace();
           }
           finally {
               ObjectMapper mapper = new ObjectMapper();
               String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
               response.getWriter().println(data);
           }
       }
       else{
           User user = new User();
           try{
               user = this.userBean.show(id);
               msg = "User fetched successfully";
           }catch(Exception ex){
               msg = ex.getMessage();
               ex.printStackTrace();
           }
           finally {
               ObjectMapper mapper = new ObjectMapper();
               String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
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
        boolean created = false;

//        User user = null; //= this.userBean.mapRequestParams(this.getRequestParams(request));
//        try{
//            user = this.userBean.mapRequestParams(this.getRequestParams(request));
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }finally {
//            ObjectMapper mapper = new ObjectMapper();
//            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
//            response.getWriter().println(data);
//        }

        try{
            this.userBean.create(this.getRequestParams(request));
            msg = "User created successfully";
            created = true;
        }catch (Exception ex) {
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNodes = mapper.createObjectNode();
            jsonNodes.put("msg",msg);
            jsonNodes.put("created", created);
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
        boolean updated = false;
        try{
            this.userBean.update(this.getRequestParams(request));
            msg = "User Updated successfully";
            updated = true;
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNodes = mapper.createObjectNode();
            jsonNodes.put("msg",msg);
            jsonNodes.put("updated", updated);
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
        boolean deleted = false;
        try{
            this.userBean.delete(id);
            msg = "User deleted successfully";
            deleted = true;
        }catch (Exception ex){
            msg = ex.getMessage();
            ex.printStackTrace();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonNodes = mapper.createObjectNode();
            jsonNodes.put("msg",msg);
            jsonNodes.put("deleted", deleted);
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
