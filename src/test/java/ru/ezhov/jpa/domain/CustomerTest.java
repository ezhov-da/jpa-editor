package ru.ezhov.jpa.domain;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerTest.class.getName());

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("ru.ezhov.jpa.test");
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
    }

    @Test
    public void selectAll() throws Exception {
        List<Customer> customerList =
                entityManager.createQuery("SELECT c FROM Customer c").getResultList();
        LOG.info(customerList.toString());
        assertFalse(customerList.isEmpty());
    }
}