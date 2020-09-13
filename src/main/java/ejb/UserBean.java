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

@Stateless
@Remote
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
        String firstName = userDetails.get("first-name");
        String lastName = userDetails.get("last-name");
        String email = userDetails.get("email");
        String telephone = userDetails.get("telephone");
        int groupId = Integer.parseInt(userDetails.get("user-group"));
        String password = "secret"; //TODO generate random password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try {
            this.user.setPerson(new Person());
            this.user.getPerson().setFirstName(firstName);
            this.user.getPerson().setLastName(lastName);
            this.user.getPerson().setEmail(email);
            this.user.getPerson().setTelephone(telephone);
            this.user.getPerson().setPassword(hashedPassword);
            this.user.getPerson().setDateAdded(new Date());
            this.user.getPerson().setStatus(true);
            this.user.setUserGroup(this.em.getReference(UserGroup.class, groupId));
            this.em.persist(this.user);
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
        catch(NoResultException ex){
            throw new Exception("No users found");
        }
    }

    /**
     * Update user
     * @param userDetails
     * @throws Exception
     */
    public void update(HashMap<String, String> userDetails) throws Exception {
        String id = userDetails.get("id");
        String firstName = userDetails.get("first-name");
        String lastName = userDetails.get("last-name");
        String email = userDetails.get("email");
        String telephone = userDetails.get("telephone");
        int groupId = Integer.parseInt(userDetails.get("user-group"));
        String password = "secret"; //TODO read user input password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        this.findById(id);
        try{
            this.user.getPerson().setFirstName(firstName);
            this.user.getPerson().setLastName(lastName);
            this.user.getPerson().setEmail(email);
            this.user.getPerson().setTelephone(telephone);
            this.user.getPerson().setPassword(hashedPassword);
            this.user.setUserGroup(this.em.find(UserGroup.class, groupId));
            this.em.merge(this.user);
        }catch(IllegalArgumentException ex){
            throw new Exception("Entity User is not an instance or is removed");
        }catch(TransactionalException ex) {
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * Show user by id
     * @param id
     * @return
     * @throws Exception
     */
    public User show(String id) throws Exception {
        this.findById(id);
        return this.user;
    }

    /**
     * Delete user by id
     * @param id
     * @throws Exception
     */
    public void delete(String id) throws Exception{
        this.findById(id);
        try {
            this.em.remove(this.user);
        }catch(TransactionalException ex){
            throw new Exception("There is no transaction for this entity manager");
        }
    }

    /**
     * Find user by id
     * @param id
     * @return
     * @throws Exception
     */
    public void findById(String id) throws Exception{
        int userId = 0;
        if(StringUtils.isBlank(id))
            throw new Exception("Invalid user id");
        else
            userId = Integer.parseInt(id);

        if(userId == 0)
            throw new Exception("Invalid user id");

        try{
            this.user = this.em.find(User.class, userId);

            if(this.user.getId() == 0)
                throw new Exception("User not found");

        }catch (IllegalArgumentException ex){
            throw new Exception("Provide a valid user entity or primary key");
        }
    }
}
