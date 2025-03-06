package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService {
    @Autowired
    AddressBookRepository addressBookRepository;

//    Method to get all the contacts in the database
    public List<ContactDTO> getAllContacts() {
        return addressBookRepository.findAll()
                .stream().map(contact -> new ContactDTO(contact.getName(), contact.getEmail(), contact.getPhone()))
                .collect(Collectors.toList());
    }

    // Method to get a single contact from the database with the help of id
    public Optional<ContactDTO> getContact(long id) {
        return addressBookRepository.findById(id).map(contact -> new ContactDTO(contact.getName(), contact.getEmail(), contact.getPhone()));
    }

    // Method to save a contact in the database
    public String saveContact(ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhone());
        addressBookRepository.save(contact);
        return "The contact has been saved successfully: " + contact.getId();
    }

    // Method to update a contact with the help of id
    public String updateContact(long id, ContactDTO contactDTO) {
        if (addressBookRepository.existsById(id)) {
            Optional<Contact> newContact = addressBookRepository.findById(id).map(contact -> {
                contact.setName(contactDTO.getName());
                contact.setEmail(contactDTO.getEmail());
                contact.setPhone(contactDTO.getPhone());
                addressBookRepository.save(contact);
                return contact;
            });
            return "The contact has been updated successfully: ";
        }
        return "Id not found!";
    }

    // Method to delete a record from the database with the help of id
    public String deleteContact(long id) {
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id);
            return "The contact has been deleted successfully!";
        }
        return "Id not found!";
    }
}
