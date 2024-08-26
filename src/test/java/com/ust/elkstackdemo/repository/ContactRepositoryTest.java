package com.ust.elkstackdemo.repository;

import com.ust.elkstackdemo.model.Contact;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@Nested
@SpringBootTest
class ContactRepositoryTest {
    ContactRepository contactRepository;
    Contact validContact, withEmptyPhoneNumber, withNullPhoneNumber,
    withNullName, withEmptyName;

    /**
     * Test cases to be generated
     *
     * 1. addContact_WithValidContact_ShouldAddContact
     * 2. addContact_WithNullName_ShouldThrowException
     * 3. addContact_WithEmptyName_ShouldThrowException
     * 4. addContact_WithNullPhoneNumber_ShouldThrowException
     * 5. addContact_WithEmptyPhoneNumber_ShouldThrowException
     * 6. addContact_WithExistingContact_ShouldThrowException
     * 7. getContacts_WithNoContacts_ShouldReturnEmptyList
     * 8. getContacts_WithContacts_ShouldReturnAllContacts
     * 9. getContactsByPhoneNumber_WithExistingPhoneNumber_ShouldReturnContact
     * 10. getContactsByPhoneNumber_WithNonExistingPhoneNumber_ShouldReturnEmptyList
     * 11. getContactsByName_WithExistingName_ShouldReturnContact
     * 12. getContactsByName_WithNonExistingName_ShouldReturnEmptyList
     * 13. removeContactByPhoneNumber_WithExistingPhoneNumber_ShouldRemoveContact
     * 14. removeContactByPhoneNumber_WithNonExistingPhoneNumber_ShouldThrowException
     * 15. removeContactByPhoneNumber_WithLongerPhoneNumber_ShouldThrowException
     * 16. removeContactByPhoneNumber_WithNullPhoneNumber_ShouldThrowException
     * 17. removeContactByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException
     * 18. getContactsByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException
     * 19. getContactsByName_WithNullName_ShouldThrowException
     * 20. getContactsByName_WithEmptyName_ShouldThrowException
     *
     * Instructions:
     * 1. While checking for exceptions, ensure the correct exception object is throw
     * and the exception message is correct.
     *
     * 2. Use the DisplayName annotation to give a meaningful name to the test case.
     *
     * 3. Use the @BeforeEach annotation to initialize the ContactRepository object.
     *
     * 4. Ensure code coverage of the ContactRepository class is 100%.
     */

    @BeforeEach
    void setUp() {
        this.contactRepository = new ContactRepository();
        validContact = new Contact("Rakesh","rakesh@gmail.com","1234567890");
        withEmptyPhoneNumber = new Contact("Rakesh", "", "");
        withNullPhoneNumber = new Contact("Rakesh", "rakesh@gmail.com", null);
        withNullName = new Contact(null, "XXXXXXXXXXXXXXXX", "1234567890");
        withEmptyName = new Contact("", "XXXXXXXXXXXXXXXX", "1234567890");
    }


    @Test
    @DisplayName("Add contacts with valid contact should add contact")
    void addContact_WithValidContact_ShouldAddContact() {
        contactRepository.addContact(validContact);
        assertEquals(1, contactRepository.getContacts().size());
    }
//    ContactRepository contactRepository;
//    Contact validContact, contactWithNullName
//            , contactWithEmptyName, contactWithNullPhoneNumber
//            , contactWithEmptyPhoneNumber, existingContact;

//    @BeforeEach
//    void setUp() {
////        contactRepository = new ContactRepository(); // not needed because of @Autowired and @SpringBootTest
//        validContact = new Contact("John Doe", "john.doe", "1234567890");
//        contactWithNullName = new Contact(null, "john.doe", "1234567890");
//        contactWithEmptyName = new Contact("", "john.doe", "1234567890");
//        contactWithNullPhoneNumber = new Contact("John Doe", "john.doe", null);
//        contactWithEmptyPhoneNumber = new Contact("John Doe", "john.doe", "");
//        existingContact = new Contact("John Doe", "john.doe", "1234567890");
//    }

//    @Test
//    @DisplayName("Add contact with valid contact should add contact")
//    void addContact_WithValidContact_ShouldAddContact() {
//        contactRepository.addContact(validContact);
//        assertEquals(1, contactRepository.getContacts().size());
//        assertEquals(validContact, contactRepository.getContacts().get(0));
//    }

    @Test
    @DisplayName("Add contact with null name should throw exception")
    void addContact_WithNullName_ShouldThrowException(){
        assertThrows(IllegalArgumentException.class, () -> contactRepository.addContact(withNullName));
    }

    @Test
    @DisplayName("Add contact with empty name should throw exception")
    void addContact_WithEmptyName_ShouldThrowException(){
        assertThrows(IllegalArgumentException.class,()->contactRepository.addContact(withEmptyName));
    }

    @Test
    @DisplayName("Add contact with null phoneNumber should throw exception")
    void addContact_WithNullPhoneNumber_ShouldThrowException(){
        assertThrows(IllegalArgumentException.class, () -> contactRepository.addContact(withNullPhoneNumber));
    }

    @Test
    @DisplayName("Add contact with empty PhoneNumber should throw exception")
    void addContact_WithEmptyPhoneNumber_ShouldThrowException(){
        assertThrows(IllegalArgumentException.class, () -> contactRepository.addContact(withEmptyPhoneNumber));
    }

    @Test
    @DisplayName("Add contact with existing contact should throw exception")
    void addContact_WithExistingContact_ShouldThrowException(){
        contactRepository.addContact(validContact);
        assertThrows(IllegalArgumentException.class, () -> contactRepository.addContact(validContact));
    }

    @Test
    @DisplayName("Get contacts with no contacts should return Empty List")
    void getContacts_WithNoContacts_ShouldReturnEmptyList(){
        assertTrue(contactRepository.getContacts().isEmpty());
    }

    @Test
    @DisplayName("Get contacts with contacts should return all contacts")
    void getContacts_WithContacts_ShouldReturnAllContacts(){
        Contact contact1 = new Contact("John", "john@example.com", "1234567890");
        Contact contact2 = new Contact("Jane", "jane@example.com", "9876543210");
        contactRepository.addContact(contact1);
        contactRepository.addContact(contact2);
        assertEquals(2, contactRepository.getContacts().size());
    }

    @Test
    @DisplayName("Get contacts by phoneNumber with existing PhoneNumber should return contact")
    void getContactsByPhoneNumber_WithExistingPhoneNumber_ShouldReturnContact(){
        Contact contact = new Contact("John", "john@example.com", "1234567890");
        contactRepository.addContact(contact);
        assertEquals(contact, contactRepository.getContactsByPhoneNumber("1234567890").get(0));
    }

    @Test
    @DisplayName("Get contacts by phoneNumber with non-existing PhoneNumber should return empty list")
    void getContactsByPhoneNumber_WithNonExistingPhoneNumber_ShouldReturnEmptyList(){
        assertEquals(new ArrayList<>(), contactRepository.getContactsByPhoneNumber("1234567890"));
    }

    @Test
    @DisplayName("Get contacts by name with existing name should return contact")
    void getContactsByName_WithExistingName_ShouldReturnContact(){
        contactRepository.addContact(validContact);
        assertTrue(contactRepository.getContactsByName("Rakesh").contains(validContact));
    }

    @Test
    @DisplayName("Get contacts by name with non existing name should return empty list")
    void getContactsByName_WithNonExistingName_ShouldReturnEmptyList(){
        assertEquals(new ArrayList<>(), contactRepository.getContactsByName("Rakesh"));
    }

    @Test
    @DisplayName("Remove contact by phoneNumber with existing phoneNumber should remove contact")
    void removeContactByPhoneNumber_WithExistingPhoneNumber_ShouldRemoveContact(){
        contactRepository.addContact(validContact);
        contactRepository.removeContactByPhoneNumber("1234567890");
        assertTrue(contactRepository.getContactsByPhoneNumber("1234567890").isEmpty());

    }

    @Test
    @DisplayName("Remove contact by phoneNumber with non-existing phoneNumber should throw exception")
    void removeContactByPhoneNumber_WithNonExistingPhoneNumber_ShouldThrowException(){
//        assertThrows(IllegalArgumentException.class, ()->{ contactRepository.removeContactByPhoneNumber("1234567890");}, "No contact with phone number");
        IllegalArgumentException thrownMessage=assertThrows(IllegalArgumentException.class,()-> contactRepository.removeContactByPhoneNumber("1234567890"));
        assertEquals("No contact found", thrownMessage.getMessage());
    }

    @Test
    @DisplayName("Remove contact by phoneNumber with longer phoneNumber should throw exception")
    void removeContactByPhoneNumber_WithLongerPhoneNumber_ShouldThrowException(){
        assertEquals("Invalid phone number",
                assertThrows(IllegalArgumentException.class,()-> contactRepository.removeContactByPhoneNumber("1234567890123")).getMessage());
    }

    @Test
    @DisplayName("Remove contact by phoneNumber with null phoneNumber should throw exception")
    void removeContactByPhoneNumber_WithNullPhoneNumber_ShouldThrowException(){
        assertEquals("Invalid phone number",
                assertThrows(IllegalArgumentException.class,()-> contactRepository.removeContactByPhoneNumber("1234567890123")).getMessage());
    }


    @Test
    @DisplayName("Remove contact by phoneNumber with alphanumeric phoneNumber should throw exception")
    void removeContactByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException(){
        assertEquals("Phone number is not a number",
            assertThrows(IllegalArgumentException.class,()-> contactRepository.removeContactByPhoneNumber("123ABCDERT")).getMessage());
    }

    //getContactsByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException
    @Test
    @DisplayName("Get contacts by phoneNumber with non-numeric phoneNumber should throw exception")
    void getContactsByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException(){
        assertEquals("Phone number is not a number",
                assertThrows(IllegalArgumentException.class,()-> contactRepository.getContactsByPhoneNumber("ACBCEDTDGT")).getMessage());
    }


    @Test
    @DisplayName("Get contacts by name with null name should throw exception")
    void getContactsByName_WithNullName_ShouldThrowException(){
        assertEquals("Name is null or empty.",
                assertThrows(IllegalArgumentException.class,()-> contactRepository.getContactsByName(null)).getMessage());
    }

    @Test
    @DisplayName("Get contacts by name with empty name should throw exception")
    void getContactsByName_WithEmptyName_ShouldThrowException(){
        assertEquals("Name is null or empty.",
                assertThrows(IllegalArgumentException.class,()-> contactRepository.getContactsByName("")).getMessage());
    }

//    @Test
//    void addContact_WithNullName_ShouldThrowException() {
//        var ex = assertThrows(IllegalArgumentException.class, () -> contactRepository.addContact(contactWithNullName));
//        assertEquals("Name is null or empty.", ex.getMessage());
//    }

}