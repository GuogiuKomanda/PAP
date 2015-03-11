package lt.pap.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import lt.pap.model.User;
import lt.pap.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserListBean implements Serializable
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1046431391905019637L;

	@Autowired
    private UserService userService;
    
    private List<User>  userList;
    
    @PostConstruct
    void init()
    {
        userList = userService.findAll();
    }
    
    public List<User> getUserList()
    {
        return userList;
    }
    
}
