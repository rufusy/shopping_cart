package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionalException;

import models.Person;
import models.User;
import models.UserGroup;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

//@Stateless
//@Remote
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    private User user = new User();

    /**
     * save new user
     * @param userDetails
     * @throws Exception
     */
    public void create(HashMap<String, String> userDetails) throws Exception{
       User user = this.mapRequestParams(userDetails);
        try {
            this.em.persist(user);
        }catch (EntityExistsException ex){
            throw new Exception("The entity user already exists");
        }catch(IllegalArgumentException ex){
            throw new Exception("The instance user is not an entity");
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * List users
     * @return
     * @throws Exception
     */
    public List<User> list() throws Exception{
        String hql = "SELECT U FROM User U";
        try{
            return this.em.createQuery(hql).getResultList();
        }catch(IllegalArgumentException ex){
            throw new Exception("Invalid query");
        }
    }

    /**
     * Update user
     * @param userDetails
     * @throws Exception
     */
    public void update(HashMap<String, String> userDetails) throws Exception {
        User user = this.mapRequestParams(userDetails);
        try{
            this.em.merge(user);
        }catch(IllegalArgumentException ex){
            throw new Exception("Entity User is not an instance or is removed");
        }catch(TransactionalException ex) {
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * Show user by id
     * @param userId
     * @return
     * @throws Exception
     */
    public User show(int userId) throws Exception {
        this.user = this.findById(userId);
        if(this.user != null)
            return this.findById(userId);
        else
            throw new Exception("User of id: " + userId + "not found");
    }

    /**
     * Delete user by id
     * @param userId
     * @throws Exception
     */
    public void delete(int userId) throws Exception{
        this.user = this.findById(userId);
        if(this.user != null)
            try {
                this.em.remove(this.findById(userId));
            }catch(TransactionalException ex){
                throw new Exception("There is no transaction for this entity manager");
            }
        else
            throw new Exception("User of id: " + userId + "not found");
    }

    /**
     * Find user by id
     * @param userId
     * @return
     * @throws Exception
     */
    public User findById(int userId) throws Exception{
        try{
            return this.em.find(User.class, userId);
        }catch (IllegalArgumentException ex){
            throw new Exception("Provide a valid user entity or primary key");
        }
    }

    /**
     * Assign request params to User entity props
     * @param userDetails
     * @throws Exception
     */
    public User mapRequestParams(HashMap<String, String> userDetails){
        int userId = 0;
        boolean updating =true;
        String id = userDetails.get("id");
        if(StringUtils.isBlank(id))
            updating = false;
        else
            userId = Integer.parseInt(id);

        String firstName = userDetails.get("first-name");
        String lastName = userDetails.get("last-name");
        String email = userDetails.get("email");
        String telephone = userDetails.get("telephone");
        int groupId = Integer.parseInt(userDetails.get("user-group"));

//        if(!updating){
            User user = new User();
            user.setPerson(new Person());
            String password = "secret"; //TODO generate random password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.getPerson().setPassword(hashedPassword);
            user.getPerson().setDateAdded(new Date());
            user.getPerson().setStatus(true);
//        }else
//            user = this.findById(userId);

        user.getPerson().setFirstName(firstName);
        user.getPerson().setLastName(lastName);
        user.getPerson().setEmail(email);
        user.getPerson().setTelephone(telephone);
        //user.setUserGroup(this.em.getReference(UserGroup.class, groupId));
        return user;
    }
}
