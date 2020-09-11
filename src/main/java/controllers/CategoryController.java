package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ejb.CategoryBean;
import models.Category;
import models.Common;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/admin/categories"})
public class CategoryController extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String msg = "";
        HashMap<String, String> newCategoryDetails = new HashMap<>();

        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()){
            Object obj = en.nextElement();
            String paramName = (String)obj;
            String paramValue = request.getParameter(paramName);
            newCategoryDetails.put(paramName, paramValue);
        }
        try{
            this.categoryBean.create(newCategoryDetails);
            msg = "Category created successfully";
        }catch (Exception ex){
            ex.printStackTrace();
            msg = "Category not created";
        }
        finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            json.put("msg", msg);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }
    }
}
