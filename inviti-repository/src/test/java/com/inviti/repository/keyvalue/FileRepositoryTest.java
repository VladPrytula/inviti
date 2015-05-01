package com.inviti.repository.keyvalue;

import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.utils.UUIDs;
import com.google.common.collect.ImmutableSet;
import com.inviti.model.domainmodel.DataStore;
import com.inviti.repository.config.CassandraConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CassandraConfiguration.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class FileRepositoryTest{
    @Autowired
    FileRepository fileRepository;

    @Before
    public void cleanData(){
        fileRepository.deleteAll();
    }


    @Test
    //@Transactional there should be a bean defined as transaction manager , TODO
    public void saveBinaryFile() throws IOException {
        BufferedImage image = null;
        URL imageURL = new URL("http://exmoorpet.com/wp-content/uploads/2012/08/cat.png");
        image = ImageIO.read(imageURL);
        fileRepository.save(new DataStore(UUIDs.timeBased(),"test","test", ImmutableSet.of("tag1", "tag3")));
        Assert.assertEquals(fileRepository.findAll().iterator().next().getType(),"test");
    }
}