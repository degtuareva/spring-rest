package product.star.contact.manager;

import java.util.List;
import java.util.Optional;

public interface ContactDao {

    List<Contact> getAllContacts();

    Optional<Contact> getContactById(Long id);

    Contact addContact(Contact contact);

    void updatePhoneNumber(Long id, String newPhoneNumber);

    void updateEmail(Long id, String newEmail);

    void deleteContactById(Long id);
}
