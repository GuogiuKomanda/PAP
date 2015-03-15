package lt.pap.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import lt.pap.config.SpringDataConfig;
import lt.pap.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringDataConfig.class})
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testFindAll() {
    List<User> users = userService.findAll();
    assertEquals(3, users.size());
  }

  @Test
  public void testFindOne() {
    User user = userService.findOne(2);
    assertEquals("Andrius", user.getName());
  }

}
