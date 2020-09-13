package ejb;

import models.UserGroup;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

@Stateless
@Remote
public class UserGroupBean {
    @PersistenceContext
    private EntityManager em;

    private UserGroup userGroup = new UserGroup();

    public void create(HashMap<String, String> newGroupDetails) throws Exception{
        String groupName = newGroupDetails.get("group-name");
        String permissions = newGroupDetails.get("permissions");
        this.userGroup.setName(groupName);
        this.userGroup.setPermission(permissions);
        this.em.persist(this.userGroup);
    }


    @SuppressWarnings({"unchecked"})
    public List<UserGroup> list() throws Exception{
        String hql = "SELECT UG FROM UserGroup UG";
        try {
            return this.em.createQuery(hql).getResultList();
        }catch (IllegalArgumentException ex){
            throw new Exception("Invalid query");
        }
    }

    public void delete(int groupId) throws Exception{
        try{
            this.userGroup = this.em.find(UserGroup.class, groupId);
            if(this.userGroup == null)
                throw new Exception("The group not found");
            else
                this.em.remove(this.userGroup);
        }catch (IllegalArgumentException ex) {
            throw new Exception("The group not found");
        }
    }
}
