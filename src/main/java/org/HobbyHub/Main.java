package org.HobbyHub;

import jakarta.persistence.EntityManagerFactory;
import org.HobbyHub.config.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("HobbyHub");
    }
}