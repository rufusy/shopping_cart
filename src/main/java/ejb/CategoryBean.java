package ejb;

import models.Category;
import models.Common;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionalException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Stateless
@Remote
public class CategoryBean {

    @PersistenceContext
    private EntityManager em;

    private Category category = new Category();

    /**
     * save new category
     * @param categoryDetails
     * @throws Exception
     */
    public void create(HashMap<String, String> categoryDetails) throws Exception{
        String name = categoryDetails.get("name");
        String description = categoryDetails.get("description");
        String metatitle = categoryDetails.get("metatitle");
        String metakeyword = categoryDetails.get("metakeyword");
        String metadescription = categoryDetails.get("metadescription");
        int parentId = Integer.parseInt(categoryDetails.get("parent-category"));
        try {
            this.category.setName(name);
            this.category.setDescription(description);
            this.category.setMetaTitle(metatitle);
            this.category.setMetaKeyword(metakeyword);
            this.category.setMetaDescription(metadescription);
            this.category.setCommon(new Common());
            this.category.getCommon().setStatus(true);
            this.category.getCommon().setDateAdded(new Date());
            this.category.getCommon().setDateModified(new Date());
            this.category.setParentCategory(this.em.getReference(Category.class, parentId));
            this.em.merge(this.category);
        }catch (EntityExistsException ex){
            throw new Exception("The entity category already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance category is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * List categories
     * @return
     * @throws Exception
     */
    public List<Category> list() throws Exception{
        String hql = "SELECT U FROM Category U";
        try{
            return this.em.createQuery(hql).getResultList();
        }catch (IllegalArgumentException ex){
            throw new Exception("Invalid query");
        }catch (NoResultException ex){
            throw new Exception("No users found");
        }
    }

    /**
     * update category by id
     * @param categoryDetails
     * @throws Exception
     */
    public void update(HashMap<String, String> categoryDetails) throws Exception {
        String id = categoryDetails.get("id");
        String name = categoryDetails.get("name");
        String description = categoryDetails.get("description");
        String metatitle = categoryDetails.get("metatitle");
        String metakeyword = categoryDetails.get("metakeyword");
        String metadescription = categoryDetails.get("metadescription");
        int parentId = Integer.parseInt(categoryDetails.get("parent-category"));
        this.category = this.findById(id);
        try{
            this.category.setName(name);
            this.category.setDescription(description);
            this.category.setMetaTitle(metatitle);
            this.category.setMetaKeyword(metakeyword);
            this.category.setMetaDescription(metadescription);
            this.category.setCommon(new Common());
            this.category.getCommon().setStatus(true);
            this.category.getCommon().setDateModified(new Date());
            this.category.setParentCategory(this.em.getReference(Category.class, parentId));
            this.em.merge(this.category);
        }catch(IllegalArgumentException ex){
            throw new Exception("Entity category is not an instance or is removed");
        }catch(TransactionalException ex) {
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * Show category by id
     * @param id
     * @return
     * @throws Exception
     */
    public Category show(String id) throws Exception {
        return this.findById(id);
    }

    /**
     * Delete category by id
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception{
        try {
            Category parent = this.findById(id);
            for(Category child : parent.getChildCategories()){
                child.setParentCategory(null);
                this.em.merge(child);
            }
            this.em.remove(parent);
        }catch (TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * Find category by id
     * @param id
     * @return
     * @throws Exception
     */
    public Category findById(String id) throws Exception{
        if(StringUtils.isBlank(id) || StringUtils.equalsIgnoreCase(id, "0"))
            throw new Exception("Invalid category id");

        try{
            Category category = this.em.find(Category.class, Integer.parseInt(id));
            if(category == null)
                throw new Exception("Category not found");
            else
                return category;

        }catch (IllegalArgumentException ex){
            throw new Exception("Provide a valid category entity or primary key");
        }
    }

}
