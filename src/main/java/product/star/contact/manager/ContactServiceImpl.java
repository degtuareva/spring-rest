package product.star.contact.manager;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;


@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private static final Logger logger= LoggerFactory.getLogger(ContactServiceImpl.class);


    private final ContactRepository contactRepository;


    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        logger.info("Fetching all contacts");
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        logger.info("Fetching contact by id {}", id);
        return contactRepository.findById(id);

    }

    @Override
    public Contact addContact(Contact contact) {
        logger.info("Adding contact {}", contact);
        return contactRepository.save(contact);
    }

    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
        logger.info("Updating phone number for contact id {} to {}", id, phoneNumber);
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setPhoneNumber(phoneNumber);
            contactRepository.save(contact);
        });
    }

    @Override
    public void updateEmail(Long id, String email) {
        contactRepository.findById(id).ifPresent(contact -> {
            logger.info("Updating email for contact id {} to {}", id, email);
            contact.setEmail(email);
            contactRepository.save(contact);
        });
    }

    @Override
    public void deleteContact(Long id) {
        logger.info("Deleting contact id {}", id);
        contactRepository.deleteById(id);
    }
}
