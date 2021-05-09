package cqrs;

import cqrs.domain.Address;
import cqrs.domain.Contact;
import cqrs.eventSource.events.EventStore;
import cqrs.eventSource.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EventSourceTest {
    private EventStore eventStore;
    private UserService userService;

    @BeforeEach
    public void init() {
        eventStore = new EventStore();
        userService = new UserService(eventStore);
    }

    @Test
    public void should_createUser() {
        userService.createUser("kelvinyhleung", "kelvin", "leung");

        var user = userService.createFromState("kelvinyhleung");
        assertEquals("kelvin", user.getFirstName());
        assertEquals("leung", user.getLastName());
        assertTrue(user.getContacts().isEmpty());
        assertTrue(user.getAddresses().isEmpty());
    }

    @Test
    public void should_updateUser() {
        var addr = Address.builder().state("hk").city("nt").postCode("nil").build();
        var contact = Contact.builder().type("home").detail("1234567890").build();
        userService.createUser("kelvinyhleung", "kelvin", "leung");
        userService.updateUser("kelvinyhleung", Set.of(contact), Set.of(addr));

        var user = userService.createFromState("kelvinyhleung");
        assertEquals("kelvin", user.getFirstName());
        assertEquals("leung", user.getLastName());
        assertFalse(user.getContacts().isEmpty());
        assertTrue(user.getAddresses().contains(addr));
        assertFalse(user.getAddresses().isEmpty());
        assertTrue(user.getContacts().contains(contact));
    }
}
