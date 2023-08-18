package org.example.services;

import org.example.entities.Planet;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class PlanetCrudService {
    private Session session;

    public PlanetCrudService() {
        session = HibernateUtil.getInstance().getSessionFactory().openSession();
    }

    public void createPlanet(Planet planet) {
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
    }

    public Planet readPlanet(String id) {
        return session.get(Planet.class, id);
    }

    public void updatePlanet(String id, String name) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Planet SET name =: name WHERE id =: planet_id")
                .setParameter("name", name)
                .setParameter("planet_id", id)
                .executeUpdate();
        transaction.commit();
    }

    public void deletePlanet(String id) {
        Planet planet = readPlanet(id);

        if (Objects.nonNull(planet)) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }

    public List<Planet> getAllPlanets() {
        return session.createQuery("select planet from Planet planet", Planet.class).list();
    }
}
