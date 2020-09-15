package ejb;

import models.StockStatus;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionalException;
import java.util.HashMap;
import java.util.List;

@Stateless
@Remote
public class StockBean {
    @PersistenceContext
    private EntityManager em;

    private StockStatus stockStatus = new StockStatus();

    /**
     * save new stock status
     * @param stockDetails
     * @throws Exception
     */
    public void create(HashMap<String, String> stockDetails) throws Exception{
        String name = stockDetails.get("name");
        try {
            this.stockStatus.setName(name);
            this.em.merge(this.stockStatus);
        }
        catch (EntityExistsException ex){
            throw new Exception("The entity stock status already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance stock status is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }


    /**
     * update stock status by id
     * @param stockDetails
     * @throws Exception
     */
    public void update(HashMap<String, String> stockDetails) throws Exception{
        String id = stockDetails.get("id");
        String name = stockDetails.get("name");
        this.stockStatus = this.findById(id);
        try {
            this.stockStatus.setName(name);
            this.em.merge(this.stockStatus);
        }
        catch(IllegalArgumentException ex){
            throw new Exception("Entity StockStatus is not an instance or is removed");
        }catch(TransactionalException ex) {
            throw new Exception("There is no transaction for this entity manager");
        }
    }


    /**
     * List stock status
     * @return
     * @throws Exception
     */
    public List<StockStatus> list() throws Exception{
        String hql = "SELECT S FROM StockStatus S";
        try{
            return this.em.createQuery(hql).getResultList();
        }catch (IllegalArgumentException ex){
            throw new Exception("Invalid query");
        }catch (NoResultException ex){
            throw new Exception("No stock status found");
        }
    }

    /**
     * Show StockStatus by id
     * @param id
     * @return
     * @throws Exception
     */
    public StockStatus show(String id) throws Exception {
        return this.findById(id);
    }


    /**
     * Delete stock status by id
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception{
        try {
            this.em.remove(this.findById(id));
        }catch (TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * Find stock status by id
     * @param id
     * @return
     * @throws Exception
     */
    public StockStatus findById(String id) throws Exception{
        if(StringUtils.isBlank(id) || StringUtils.equalsIgnoreCase(id, "0"))
            throw new Exception("Invalid stock status id");

        try{
            StockStatus stockStatus = this.em.find(StockStatus.class, Integer.parseInt(id));
            if(stockStatus == null)
                throw new Exception("StockStatus not found");
            else
                return stockStatus;

        }catch (IllegalArgumentException ex){
            throw new Exception("Provide a valid StockStatus entity or primary key");
        }
    }
}
