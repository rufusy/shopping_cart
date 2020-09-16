package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.category.CategoryDto;
import dto.category.ListCategoryDto;
import dto.category.SingleCategoryDto;
import ejb.CategoryBean;
import helpers.GetRequestParamsHelper;
import models.Category;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/categories"})
public class CategoryController extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;

    /**
     * Get list of categories
     * Get a category by id
     * @param request
     * @param response
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String msg="";
        String data ="";
        boolean found = true;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode categoryNode = mapper.createObjectNode();
        if(StringUtils.isBlank(id)){
            List<Category> categories = null;
            try{
                categories = this.categoryBean.list();
                if(categories.isEmpty()){
                    found = false;
                    msg="Categories not found";
                }
            }catch(Exception ex){
                msg = ex.getMessage();
                found = false;
                ex.printStackTrace();
            }
            finally {
                if(!found){
                    categoryNode.put("msg", msg);
                    categoryNode.put("categoriesFound", false);
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryNode);
                }else
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.buildCategoryListJson(categories));

                response.getWriter().println(data);
            }
        }
        else{
            Category category = new Category();
            try{
                category = this.categoryBean.show(id);
            }catch(Exception ex){
                msg = ex.getMessage();
                found = false;
                ex.printStackTrace();
            }
            finally {
                if(!found) {
                    categoryNode.put("msg", msg);
                    categoryNode.put("categoryFound", false);
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryNode);
                }
                else
                    data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.buildCategoryJson(category));

                response.getWriter().println(data);
            }
        }
    }

    /**
     * Create new category
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String msg = "";
        boolean categoryCreated = false;

        try{
            this.categoryBean.create(GetRequestParamsHelper.getRequestParams(request));
            msg = "Category created successfully";
            categoryCreated = true;
        }catch (Exception ex) {
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode categoryNode = mapper.createObjectNode();
            categoryNode.put("msg",msg);
            categoryNode.put("categoryCreated", categoryCreated);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryNode);
            response.getWriter().println(data);
        }
    }

    /**
     * Update category
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String msg = "";
        boolean categoryUpdated = false;
        try{
            this.categoryBean.update(GetRequestParamsHelper.getRequestParams(request));
            msg = "Category Updated successfully";
            categoryUpdated = true;
        }catch (Exception ex){
            ex.printStackTrace();
            msg = ex.getMessage();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode categoryNode = mapper.createObjectNode();
            categoryNode.put("msg",msg);
            categoryNode.put("categoryUpdated", categoryUpdated);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryNode);
            response.getWriter().println(data);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String msg = "";
        boolean categoryDeleted = false;
        try{
            this.categoryBean.delete(id);
            msg = "Category deleted successfully";
            categoryDeleted = true;
        }catch (Exception ex){
            msg = ex.getMessage();
            ex.printStackTrace();
        }finally {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode categoryNode = mapper.createObjectNode();
            categoryNode.put("msg",msg);
            categoryNode.put("categoryDeleted", categoryDeleted);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoryNode);
            response.getWriter().println(data);
        }
    }

    /**
     * build category json
     * @param category
     * @return
     */
    public SingleCategoryDto buildCategoryJson(Category category){
        return new SingleCategoryDto(new CategoryDto(category));
    }

    /**
     * build categories list json
     * @param categories
     * @return
     */
    public ListCategoryDto buildCategoryListJson(List<Category> categories){
        return new ListCategoryDto(
                categories.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList())
        );
    }

}
