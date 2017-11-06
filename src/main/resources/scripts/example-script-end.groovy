} finally {
    if (entityManager != null) {
        entityManager.close()
    }
    if (entityManagerFactory != null) {
        entityManagerFactory.close()
    }
}