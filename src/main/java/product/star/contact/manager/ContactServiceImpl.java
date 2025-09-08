package product.star.contact.manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setPhoneNumber(phoneNumber);
            contactRepository.save(contact);
        });
    }

    @Override
    public void updateEmail(Long id, String email) {
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setEmail(email);
            contactRepository.save(contact);
        });
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}