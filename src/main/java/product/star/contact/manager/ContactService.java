package product.star.contact.manager;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getAllContacts();
    Optional<Contact> getContactById(Long id);
    Contact addContact(Contact contact);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateEmail(Long id, String email);
    void deleteContact(Long id);
}

