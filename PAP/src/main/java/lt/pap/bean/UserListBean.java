package lt.pap.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import lt.pap.model.User;
import lt.pap.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserListBean
{
    
    @Autowired
    private UserService userService;
    
    private List<User>  userList;
    
    public UserListBean()
    {
        userList = new ArrayList<User>();
        User u = new User();
        u.setId(5);
        u.setName("Petia");
    }
    
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
