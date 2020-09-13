package ejb;

import models.User;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class AuthenticationBean {

    @PersistenceContext
    private EntityManager em;

    /**
     * Authenticate user
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    public User authenticate(String email, String password) throws Exception{

        if(StringUtils.isBlank(email) || StringUtils.isBlank(password))
            throw new Exception("Wrong email or password");

        String hql = "SELECT U FROM User U WHERE U.person.email = :email";

        try {
            User user = (User)this.em.createQuery(hql)
                    .setParameter("email", email)
                    .getSingleResult();

            if(!BCrypt.checkpw(password, user.getPerson().getPassword()))
                throw new Exception("Wrong email or password");

            return user;
        }
        catch (NoResultException ex){
            throw new Exception("Wrong email or password");
        }
    }
}
