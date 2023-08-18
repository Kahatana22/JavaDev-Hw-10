package org.example.services;

import org.example.entities.Client;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class ClientCrudService {
    private Session session;

    public ClientCrudService() {
        session = HibernateUtil.getInstance().getSessionFactory().openSession();
    }

    public void createClient(Client client) {
        Transaction transaction = session.beginTransaction();
        session.persist(client);
        transaction.commit();
    }

    public Client readClient(Long id) {
        return session.get(Client.class, id);
    }

    public void updateClient(Long id, String name) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Client SET name =: name WHERE id =: client_id")
                .setParameter("name", name)
                .setParameter("client_id", id)
                .executeUpdate();
        transaction.commit();
    }

    public void deleteClient(Long id) {
        Client client = readClient(id);

        if (Objects.nonNull(client)) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        }
    }

    public List<Client> getAllClients() {
        return session.createQuery("select client from Client client", Client.class).list();
    }
}
