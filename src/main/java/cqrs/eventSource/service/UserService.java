package cqrs.eventSource.service;

import cqrs.domain.Address;
import cqrs.domain.Contact;
import cqrs.domain.User;
import cqrs.eventSource.events.*;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class UserService {
    private EventStore repository;

    public UserService(EventStore repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String firstName, String lastName) {
        var user = UserCreatedEvent.builder().userId(userId).firstName(firstName).lastName(lastName).build();
        repository.addEvent(userId, user);
    }

    public void updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) {
        var user = createFromState(userId);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user is not found for id: " + userId);
        }

        // remove from existing contacts
        user.getContacts().stream().filter(c -> !contacts.contains(c))
                .forEach(c -> {
                    var event = new UserContactRemovedEvent();
                    event.setContactType(c.getType());
                    event.setContactDetails(c.getDetail());
                    repository.addEvent(userId, event);
                });
        // add new contacts
        contacts.stream().filter(c -> !user.getContacts().contains(c))
                .forEach(c -> {
                    var event = new UserContactAddedEvent();
                    event.setContactType(c.getType());
                    event.setContactDetails(c.getDetail());
                    repository.addEvent(userId, event);
                });

        // remove from existing addresses
        user.getAddresses().stream().filter(a -> !addresses.contains(a))
                .forEach(address -> {
                    var event = new UserAddressRemovedEvent();
                    event.setState(address.getState());
                    event.setCity(address.getCity());
                    event.setPostCode(address.getPostCode());
                    repository.addEvent(userId, event);
                });
        // add new addresses
        addresses.stream().filter(a -> !user.getAddresses().contains(a))
                .forEach(address -> {
                    var event = new UserAddressAddedEvent();
                    event.setState(address.getState());
                    event.setCity(address.getCity());
                    event.setPostCode(address.getPostCode());
                    repository.addEvent(userId, event);
                });
    }

    public User createFromState(String userId) {
        var user = new AtomicReference<User>();

        repository.getEventByUserId(userId)
                .forEach(e -> {
                    if (e instanceof UserCreatedEvent) {
                        var event = (UserCreatedEvent) e;
                        user.set(new User(event.getUserId(), event.getFirstName(), event.getLastName()));
                    } else if (e instanceof UserAddressAddedEvent) {
                        var event = (UserAddressAddedEvent) e;
                        user.get().getAddresses().add(Address.builder()
                                .state(event.getState())
                                .city(event.getCity())
                                .postCode(event.getPostCode())
                                .build());
                    } else if (e instanceof UserContactAddedEvent) {
                        var event = (UserContactAddedEvent) e;
                        user.get().getContacts().add(Contact.builder()
                                .type(event.getContactType())
                                .detail(event.getContactDetails())
                                .build());
                    } else if (e instanceof UserAddressRemovedEvent) {
                        var event = (UserAddressRemovedEvent) e;
                        var toBeRemoved = Address.builder().state(event.getState())
                                .city(event.getCity())
                                .postCode(event.getPostCode())
                                .build();
                        user.get().getAddresses().remove(toBeRemoved);
                    } else if (e instanceof UserContactRemovedEvent) {
                        var event = (UserContactRemovedEvent) e;
                        var toBeRemoved = Contact.builder()
                                .type(event.getContactType())
                                .detail(event.getContactDetails())
                                .build();
                        user.get().getContacts().remove(toBeRemoved);
                    }
                });

        return user.get();
    }
}
