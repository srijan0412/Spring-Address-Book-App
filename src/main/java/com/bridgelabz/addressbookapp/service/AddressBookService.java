package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressBookService {
    @Autowired
    AddressBookRepository addressBookRepository;

//    Method to get all the contacts in the database
    public List<ContactDTO> getAllContacts() {
        log.info("Getting All Contacts...");
        return addressBookRepository.findAll()
                .stream().map(contact -> new ContactDTO(contact.getName(), contact.getEmail(), contact.getPhone()))
                .collect(Collectors.toList());
    }

    // Method to get a single contact from the database with the help of id
    public ContactDTO getContact(long id) {
        log.info("Getting Contact: Id={}",id);
        return addressBookRepository.findById(id).map(contact -> new ContactDTO(contact.getName(), contact.getEmail(), contact.getPhone()))
                .orElseThrow(() -> new AddressBookException("Contact with ID " + id + " not found"));
    }

    // Method to save a contact in the database
    public String saveContact(ContactDTO contactDTO) {
        log.info("Saving Contact: Name={}, Email={}, Phone={}", contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        Contact contact = new Contact(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        addressBookRepository.save(contact);
        return "The contact has been saved successfully: " + contact.getId();
    }

    // Method to update a contact with the help of id
    public String updateContact(long id, ContactDTO contactDTO) {
        if (addressBookRepository.existsById(id)) {
            log.info("Updating Contact: Name={}, Email={}, Phone={}", contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
            Optional<Contact> newContact = addressBookRepository.findById(id).map(contact -> {
                contact.setName(contactDTO.getName());
                contact.setEmail(contactDTO.getEmail());
                contact.setPhone(contactDTO.getPhone());
                addressBookRepository.save(contact);
                return contact;
            });
            return "The contact has been updated successfully: ";
        }
        log.warn("Invalid Request: Id not found!");
        return "Id not found!";
    }

    // Method to delete a record from the database with the help of id
    public String deleteContact(long id) {
        log.warn("Deleting contact with ID={}", id);
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id);
            return "The contact has been deleted successfully!";
        }
        return "Id not found!";
    }
}
