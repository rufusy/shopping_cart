package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.User;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

@Stateless
@Remote
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public User authenticate(String email, String password) throws Exception{

        if(StringUtils.isAllBlank(email) || StringUtils.isBlank(password))
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

//    public User authenticate(User user1) throws Exception{
//
//        if(StringUtils.isAllBlank(user1.getPerson().getEmail()) || StringUtils.isBlank(user1.getPerson().getPassword()))
//            throw new Exception("Wrong email or password");
//
//        String hql = "SELECT U FROM User U WHERE U.person.email = :email";
//        User user = (User)this.em.createQuery(hql)
//                .setParameter("email", user1.getPerson().getEmail())
//                .getSingleResult();
//
//        if(user == null)
//            throw new Exception("Wrong email or password");
//
//        if(!BCrypt.checkpw(user1.getPerson().getPassword(), user.getPerson().getPassword()))
//            throw new Exception("Wrong email or password");
//
//        return user;
//    }
}
