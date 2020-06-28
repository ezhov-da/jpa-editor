package ru.ezhov.jpa.editor.domain.model;

import org.h2.tools.Server;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.jpa.editor.configuration.DbService;
import ru.ezhov.jpa.editor.h2db.H2DbService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductTest {
    private static final Logger LOG = LoggerFactory.getLogger(OrderItemTest.class.getName());

    private static H2DbService h2DbService;
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        new DbService().copyDbToUser();

        h2DbService = new H2DbService();
        h2DbService.start();

        entityManagerFactory =
                Persistence.createEntityManagerFactory("ru.ezhov.jpa.editor.test");
    }

    @Before
    public void setBeforeUp() throws Exception {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void setUpAfter() throws Exception {
        entityManager.close();
    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        entityManagerFactory.close();

        h2DbService.stop();
    }

    @Test
    public void selectAll() throws Exception {
        List<Product> products =
                entityManager.createQuery("SELECT o FROM Product o").getResultList();

        LOG.info(products.toString());

        assertFalse(products.isEmpty());
    }
}