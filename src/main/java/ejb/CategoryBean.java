package ejb;

import models.Category;
import models.Common;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashMap;

@Stateless
@Remote
public class CategoryBean {

    @PersistenceContext
    private EntityManager em;

    private Category category = new Category();

    public void create(HashMap<String, String> newCategoryDetails){
        String name = newCategoryDetails.get("name");
        String description = newCategoryDetails.get("description");
        String metatitle = newCategoryDetails.get("metatitle");
        String metakeyword = newCategoryDetails.get("metakeyword");
        String metadescription = newCategoryDetails.get("metadescription");
        int parentId = Integer.parseInt(newCategoryDetails.get("parent-category"));
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
    }
}
