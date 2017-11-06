EntityManagerFactory entityManagerFactory
EntityManager entityManager

try {
    entityManagerFactory =
            Persistence.createEntityManagerFactory("ru.ezhov.jpa.test")
    entityManager = entityManagerFactory.createEntityManager()
