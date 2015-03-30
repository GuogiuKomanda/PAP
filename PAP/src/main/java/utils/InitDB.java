package utils;

import java.time.Month;
import java.time.YearMonth;

import lt.pap.model.Engine;
import lt.pap.model.FuelType;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.model.ModelEngine;
import lt.pap.model.ModelGroup;
import lt.pap.model.Part;
import lt.pap.model.PartTranslated;
import lt.pap.model.User;
import lt.pap.service.EngineService;
import lt.pap.service.FuelTypeService;
import lt.pap.service.MakeService;
import lt.pap.service.ModelEngineService;
import lt.pap.service.ModelGroupService;
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
  private ModelGroupService modelGroupService;
  
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
    
    Make makeAA = new Make();
    makeAA.setName("AA");
    
    makeService.save(makeAA);
    
    Make makeAB = new Make();
    makeAB.setName("AB");
    
    makeService.save(makeAB);
    
    Make makeBB = new Make();
    makeBB.setName("BB");
    
    makeService.save(makeBB);
    
    ModelGroup mgA = new ModelGroup();
    mgA.setMake(makeAA);
    mgA.setName("Group A");
    
    modelGroupService.save(mgA);
    
    ModelGroup mgB = new ModelGroup();
    mgB.setMake(makeBB);
    mgB.setName("Group B");
    
    modelGroupService.save(mgB);
    
    Model modelAA = new Model();
    modelAA.setModelGroup(mgA);
    modelAA.setName("modelAA");
    modelAA.setFrom(YearMonth.of(2001, 01));
    modelAA.setTo(YearMonth.of(2001, 02));
    
    modelService.save(modelAA);
    
    Model modelAA2 = new Model();
    modelAA2.setModelGroup(mgA);
    modelAA2.setName("modelAA-2");
    modelAA2.setFrom(YearMonth.of(2007, 01));
    modelAA2.setTo(YearMonth.of(2007, 02));
    
    modelService.save(modelAA2);
    
    Model modelBB = new Model();
    modelBB.setModelGroup(mgB);
    modelBB.setName("model BB");
    modelBB.setFrom(YearMonth.of(2007, 01));
    modelBB.setTo(YearMonth.of(2007, 02));
    
    modelService.save(modelBB);
    
    Engine engine = new Engine();
    engine.setCc(1000);
    engine.setKw(66);
    engine.setName("E66");
    
    engineService.save(engine);
    
    ModelEngine me = new ModelEngine();
    me.setEngine(engine);
    me.setModel(modelAA);
    me.setFrom(YearMonth.of(2000, 1));
    me.setTo(YearMonth.of(2008, 12));
    
    modelEngineService.save(me);
    
    FuelType fuelB = new FuelType();
    fuelB.setName("Benzas");
    
    fuelTypeService.save(fuelB);
    
    FuelType fuelD = new FuelType();
    fuelD.setName("Dyzelis");
    
    fuelTypeService.save(fuelD);
  }

}
