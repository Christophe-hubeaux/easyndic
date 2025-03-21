package com.easyndic.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class testHibernate {
    public static void main(String[] args) {
    // creation de l'EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    EntityManager em = emf.createEntityManager();

    // test de connexion et transaction
    try {
        em.getTransaction().begin();
        em.getTransaction().commit();
        System.out.println("Base de données initialisée avec succès !");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        em.close();
        emf.close();
    }
}
}
