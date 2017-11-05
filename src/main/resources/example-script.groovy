import ru.ezhov.jpa.domain.Customer

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

EntityManagerFactory entityManagerFactory
EntityManager entityManager

try {
    entityManagerFactory =
            Persistence.createEntityManagerFactory("ru.ezhov.jpa.test")
    entityManager = entityManagerFactory.createEntityManager()

    List<Customer> customerList =
            entityManager.createQuery("SELECT c FROM Customer c").getResultList()

    return customerList
} finally {
    if (entityManager != null) {
        entityManager.close()
    }
    if (entityManagerFactory != null) {
        entityManagerFactory.close()
    }
}
