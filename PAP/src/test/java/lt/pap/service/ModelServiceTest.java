package lt.pap.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import lt.pap.config.SpringDataConfig;
import lt.pap.model.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringDataConfig.class})


public class ModelServiceTest
{
    @Autowired
    private ModelService modelService;
    
    @Test
    public void test()
    {
        List<Model> models = modelService.findByModelGroupId(1L);
        assertEquals(2, models.size());
    }
    
}
