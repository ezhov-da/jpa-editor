import com.fasterxml.jackson.databind.ObjectMapper

import javax.persistence.Persistence
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

import ru.ezhov.jpa.editor.domain.model.Customer
import ru.ezhov.jpa.editor.domain.model.Order
import ru.ezhov.jpa.editor.domain.model.OrderItem
import ru.ezhov.jpa.editor.domain.model.Product
import ru.ezhov.jpa.editor.domain.model.Supplier

// you can use own import here >>>

// <<< end own import

EntityManagerFactory entityManagerFactory
EntityManager entityManager

try {
    entityManagerFactory =
            Persistence.createEntityManagerFactory("ru.ezhov.jpa.editor.test")
    entityManager = entityManagerFactory.createEntityManager()

    // you can edit this part >>>

    entityManager.transaction.begin()

    List<Customer> customerList =
            entityManager.createQuery("""
                SELECT 
                    c
                FROM Customer c
                """).getResultList()

    entityManager.transaction.commit()

    return customerList
    // <<< end part
} finally {
    if (entityManager != null) {
        entityManager.close()
    }
    if (entityManagerFactory != null) {
        entityManagerFactory.close()
    }
}