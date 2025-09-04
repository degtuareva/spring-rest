package product.star.contact.manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return contactDao.getAllContacts();
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        return contactDao.getContactById(id);
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactDao.addContact(contact);
    }

    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
        contactDao.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    public void updateEmail(Long id, String email) {
        contactDao.updateEmail(id, email);
    }

    @Override
    public void deleteContact(Long id) {
        contactDao.deleteContactById(id);
    }
}
