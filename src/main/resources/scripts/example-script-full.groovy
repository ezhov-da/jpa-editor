import javax.persistence.Persistence
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

import ru.ezhov.jpa.domain.Customer
import ru.ezhov.jpa.domain.Order
import ru.ezhov.jpa.domain.OrderItem
import ru.ezhov.jpa.domain.Product
import ru.ezhov.jpa.domain.Supplier

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
