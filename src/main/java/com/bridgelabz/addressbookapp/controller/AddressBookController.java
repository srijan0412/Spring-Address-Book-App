package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.model.Contact;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/addressbookapp")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return new ResponseEntity<List<ContactDTO>>(addressBookService.getAllContacts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContactDTO>> getContactById(@PathVariable Long id) {
        return new ResponseEntity<Optional<ContactDTO>>(addressBookService.getContact(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createContact(@Valid @RequestBody ContactDTO contactDTO) {
        return new ResponseEntity<String>(addressBookService.saveContact(contactDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @Valid @RequestBody ContactDTO contactDTO) {
        return new ResponseEntity<String>(addressBookService.updateContact(id, contactDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        return new ResponseEntity<String>(addressBookService.deleteContact(id), HttpStatus.OK);
    }
}
