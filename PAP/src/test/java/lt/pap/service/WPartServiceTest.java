package lt.pap.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import lt.pap.config.SpringDataConfig;
import lt.pap.model.WPart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

  @Transactional
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes = {SpringDataConfig.class})
  public class WPartServiceTest {

    @Autowired
    private WPartService wpartService;

    @Test
    public void testFindAll() {
      List<WPart> all = wpartService.findAll(); 
      List<Long> fuels = new ArrayList<Long>();
      fuels.add(1L);
      List<WPart> parts = wpartService.findByFilters2("4", new ArrayList<Long>(), fuels);
//      assertEquals(3, users.size());
      assertEquals(2, parts.size());
    }


  }