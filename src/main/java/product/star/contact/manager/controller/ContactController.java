package product.star.contact.manager.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.star.contact.manager.Contact;
import product.star.contact.manager.ContactService;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;


@RestController
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;

    public ContactController(ContactService contactService) {

        this.contactService = contactService;
        logger.info("ContactService initialized");
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        logger.info("Received request to get all contacts");
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Optional<Contact> getContactById(@PathVariable Long id) {
        logger.info("Received request to get contact by id {}",id);
        return contactService.getContactById(id);
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) {
        logger.info("Received request to add contact {}",contact);
        return contactService.addContact(contact);
    }

    @PutMapping("/{id}/phone")
    public void updatePhoneNumber(@PathVariable Long id, @RequestBody String phoneNumber) {
        logger.info("Received request to update phone number for contact id {} to {}",id,phoneNumber);
        contactService.updatePhoneNumber(id, phoneNumber);
    }

    @PutMapping("/{id}/email")
    public void updateEmail(@PathVariable Long id, @RequestBody String email) {
        logger.info("Received request to update email for contact id {} to {}",id,email);
        contactService.updateEmail(id, email);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        logger.info("Reseived request to delete contact id {}",id);
        contactService.deleteContact(id);
    }
}
