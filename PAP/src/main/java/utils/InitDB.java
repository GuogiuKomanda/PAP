package utils;

import lt.pap.model.Part;
import lt.pap.model.PartTranslated;
import lt.pap.model.User;
import lt.pap.service.PartService;
import lt.pap.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InitConfig.class})
public class InitDB {

  @Autowired
  private UserService userService;

  @Autowired
  private PartService partService;


  @Test
  public void init() {

    // clean
    // userService.deleteAll();
    // partService.deleteAll();

    // init users
    User user = new User();
    user.setName("Antanas");

    userService.save(user);

    user = new User();
    user.setName("Andrius");

    userService.save(user);

    user = new User();
    user.setName("Jonas");

    user = userService.save(user);

    // init parts
    Part part = new Part();

    PartTranslated translation = new PartTranslated();
    translation.setName("Engine");
    translation.setDescription("engine descr in english");
    part.getTranslated().put("en", translation);

    translation = new PartTranslated();
    translation.setName("Variklis");
    translation.setDescription("variklio aprašymas");
    part.getTranslated().put("lt", translation);

    partService.save(part);
  }

}
