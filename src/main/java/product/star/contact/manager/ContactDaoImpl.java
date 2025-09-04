package product.star.contact.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactDaoImpl implements ContactDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ContactDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> getAllContacts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Contact").list();
        }
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Contact contact = session.get(Contact.class, id);
            return Optional.ofNullable(contact);
        }
    }

    @Override
    public Contact addContact(Contact contact) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(contact);
            tx.commit();
            return contact;
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void updatePhoneNumber(Long id, String newPhoneNumber) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                contact.setPhoneNumber(newPhoneNumber);
                session.update(contact);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void updateEmail(Long id, String newEmail) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                contact.setEmail(newEmail);
                session.update(contact);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void deleteContactById(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                session.delete(contact);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            throw e;
        }
    }
}
