package utils;

import java.time.Month;
import java.time.YearMonth;

import lt.pap.model.Engine;
import lt.pap.model.FuelType;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.model.ModelEngine;
import lt.pap.model.Part;
import lt.pap.model.PartTranslated;
import lt.pap.model.User;
import lt.pap.service.EngineService;
import lt.pap.service.FuelTypeService;
import lt.pap.service.MakeService;
import lt.pap.service.ModelEngineService;
import lt.pap.service.ModelService;
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

  @Autowired
  private MakeService makeService;
  
  @Autowired
  private ModelService modelService;
  
  @Autowired
  private EngineService engineService;
  
  @Autowired
  private ModelEngineService modelEngineService;
  
  @Autowired
  private FuelTypeService fuelTypeService;
  
  @Test
  public void init() {

    // clean
    // userService.deleteAll();
    // partService.deleteAll();

    YearMonth date = YearMonth.of(2005, Month.JANUARY);
    // init users
    User user = new User();
    user.setName("Antanas");
    user.setDate(date);

    userService.save(user);

    date = YearMonth.of(1999, Month.MARCH);
    
    user = new User();
    user.setName("Andrius");
    user.setDate(date);

    userService.save(user);
    
    date = YearMonth.of(2000, Month.JUNE);

    user = new User();
    user.setName("Jonas");
    user.setDate(date);

    user = userService.save(user);

    // init parts
    Part part = new Part();

    PartTranslated translation = new PartTranslated();
    translation.setName("Engine");
    translation.setDescription("engine descr in english");
    part.getTranslated().put("en", translation);

    translation = new PartTranslated();
    translation.setName("Variklis");
    translation.setDescription("variklio apra\u0161ymas");
    part.getTranslated().put("lt", translation);

    partService.save(part);
    
    Make make = new Make();
    make.setMakeName("AA");
    
    makeService.save(make);
    
    make = new Make();
    make.setMakeName("AB");
    
    makeService.save(make);
    
    make = new Make();
    make.setMakeName("BB");
    
    makeService.save(make);
    
    Model model = new Model();
    model.setMake(make);
    model.setModelName("modelAA");
    model.setFrom(YearMonth.of(2001, 01));
    model.setTo(YearMonth.of(2001, 02));
    
    modelService.save(model);
    
    Engine engine = new Engine();
    engine.setCc(1000);
    engine.setKw(66);
    engine.setEngine("E66");
    
    engineService.save(engine);
    
    ModelEngine me = new ModelEngine();
    me.setEngine(engine);
    me.setModel(model);
    me.setFrom(YearMonth.of(2000, 1));
    me.setTo(YearMonth.of(2008, 12));
    
    modelEngineService.save(me);
    
    FuelType fuel = new FuelType();
    fuel.setFuel("AA");
    
    fuelTypeService.save(fuel);
  }

}
