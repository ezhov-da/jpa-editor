List<Customer> customerList =
        entityManager.createQuery("SELECT c FROM Customer c").getResultList()

return customerList