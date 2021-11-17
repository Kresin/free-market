package com.freemarket.app.common;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManager {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Free-Market");

    public javax.persistence.EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
