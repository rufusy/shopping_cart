package ejb;

import models.Product;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionalException;
import java.util.Date;
import java.util.HashMap;

@Stateless
@Remote
public class ProductBean {
    @PersistenceContext
    private EntityManager em;

    private Product product = new Product();

    /**
     * save new product
     * @param productDetails
     * @throws Exception
     */
    public void create(HashMap<String, String> productDetails) throws Exception{
        String sku = productDetails.get("sku");
        String upc = productDetails.get("upc");
        String ean = productDetails.get("ean");
        String jan = productDetails.get("jan");
        String isbn = productDetails.get("isbn");
        String mpn = productDetails.get("mpn");
        String name = productDetails.get("name");
        String description = productDetails.get("description");
        String tag = productDetails.get("tag");
        String metaTitle = productDetails.get("metaTitle");
        String metaDescription = productDetails.get("metaDescription");
        String metaKeyword = productDetails.get("metaKeyword");
        int quantity = Integer.parseInt(productDetails.get("quantity"));
        boolean shipping;
        if(StringUtils.equalsIgnoreCase(productDetails.get("shipping"), "yes"))
            shipping = true;
        else
            shipping = false;
        double price = Double.parseDouble(productDetails.get("price"));
        String related = productDetails.get("related");
        double weight = Double.parseDouble(productDetails.get("weight"));
        double length = Double.parseDouble(productDetails.get("length"));
        double width = Double.parseDouble(productDetails.get("width"));
        double height = Double.parseDouble(productDetails.get("height"));
        int minimum = Integer.parseInt(productDetails.get("minimum"));

        try{
            this.product.setSku(sku);
            this.product.setUpc(upc);
            this.product.setEan(ean);
            this.product.setJan(jan);
            this.product.setIsbn(isbn);
            this.product.setMpn(mpn);
            this.product.setName(name);
            this.product.setDescription(description);
            this.product.setTag(tag);
            this.product.setMetaTitle(metaTitle);
            this.product.setMetaDescription(metaDescription);
            this.product.setMetaKeyword(metaKeyword);
            this.product.setQuantity(quantity);
            this.product.setShipping(shipping);
            this.product.setPrice(price);
            this.product.setRelated(related);
            this.product.setWeight(weight);
            this.product.setLength(length);
            this.product.setWidth(width);
            this.product.setHeight(height);
            this.product.setMinimum(minimum);
            this.product.getCommon().setStatus(true);
            this.product.getCommon().setDateAdded(new Date());
            this.product.getCommon().setDateModified(new Date());
            this.em.merge(this.product);
        }catch (EntityExistsException ex){
            throw new Exception("The entity product already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance product is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }
}
