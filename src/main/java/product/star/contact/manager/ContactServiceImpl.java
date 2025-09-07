package product.star.contact.manager;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        return Optional.empty();
    }

    @Override
    public Contact addContact(Contact contact) {

        return contactDao.save(contact);
    }

    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
        contactDao.findById(id).ifPresent(contact -> {
            contact.setPhoneNumber(phoneNumber);
            contactDao.save(contact);
        });
    }

    @Override
    public void updateEmail(Long id, String email) {
        contactDao.findById(id).ifPresent(contact -> {
            contact.setEmail(email);
            contactDao.save(contact);
        });
    }

    @Override
    public void deleteContact(Long id) {

        contactDao.deleteById(id);
    }
}