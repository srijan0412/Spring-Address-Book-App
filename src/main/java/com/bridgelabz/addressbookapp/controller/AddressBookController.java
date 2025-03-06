package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbookapp")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<String> getAllContacts() {
        return ResponseEntity.ok("Get request successful!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok("Get request successful!");
    }

    @PostMapping
    public ResponseEntity<String> createContact() {
        return ResponseEntity.ok("Post request successful!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id) {
        return ResponseEntity.ok("Put request successful!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        return ResponseEntity.ok("Delete request successful!");
    }
}
