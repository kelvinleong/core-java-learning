package cqrs;

import cqrs.cqrs.aggregates.UserAggregate;
import cqrs.cqrs.commands.CreateUserCommand;
import cqrs.cqrs.commands.UpdateUserCommand;
import cqrs.cqrs.projections.UserProjection;
import cqrs.cqrs.projectors.UserProjector;
import cqrs.cqrs.queries.AddressByRegionQuery;
import cqrs.cqrs.queries.ContactByTypeQuery;
import cqrs.cqrs.repository.UserReadRepository;
import cqrs.cqrs.repository.UserWriteRepository;
import cqrs.domain.Address;
import cqrs.domain.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CqrsTest {
    private UserWriteRepository writeRepository;
    private UserReadRepository readRepository;
    private UserProjector projector;
    private UserAggregate userAggregate;
    private UserProjection userProjection;

    @BeforeEach
    public void setUp() {
        writeRepository = new UserWriteRepository();
        readRepository = new UserReadRepository();
        projector = new UserProjector(readRepository);
        userAggregate = new UserAggregate(writeRepository);
        userProjection = new UserProjection(readRepository);
    }

    @Test
    public void should_createUser_givenCreateCommand() {
        var uuid = UUID.randomUUID().toString();
        var cmd = CreateUserCommand.builder().userId(uuid)
                .firstName("kelvin")
                .lastName("liang")
                .build();
        var user = userAggregate.handleCreatedUserCommand(cmd);
        projector.project(user);

        var r = writeRepository.getUser(uuid);
        assertNotNull(r);
        assertEquals("kelvin", r.getFirstName());
        assertEquals("liang", r.getLastName());
        assertEquals(uuid, r.getUserId());
        assertTrue(r.getAddresses().isEmpty());
        assertTrue(r.getContacts().isEmpty());
        assertTrue(readRepository.getUserAddress(uuid).getAddressByRegion().isEmpty());
        assertTrue(readRepository.getUserContact(uuid).getContactByType().isEmpty());
    }

    @Test
    public void should_updateUser_givenUpdateCommand() {
        var uuid = UUID.randomUUID().toString();
        var createUserCommand = CreateUserCommand.builder().userId(uuid)
                .firstName("kelvin")
                .lastName("liang")
                .build();
        var user = userAggregate.handleCreatedUserCommand(createUserCommand);
        projector.project(user);

        var updateUserCommand = UpdateUserCommand.builder().userId(uuid)
                .addresses(new HashSet<>())
                .contacts(new HashSet<>())
                .build();
        updateUserCommand.getAddresses().add(Address.builder().city("nt").state("hk").build());
        updateUserCommand.getContacts().add(Contact.builder().type("home").detail("1234").build());
        user = userAggregate.handleUpdateUserCommand(updateUserCommand);
        projector.project(user);

        var r = writeRepository.getUser(uuid);
        assertNotNull(r);
        assertEquals("kelvin", r.getFirstName());
        assertEquals("liang", r.getLastName());
        assertEquals(uuid, r.getUserId());
        assertEquals(1, r.getAddresses().size());
        assertEquals(1, r.getContacts().size());
        assertEquals(1, readRepository.getUserAddress(uuid).getAddressByRegion().size());
        assertEquals(1, readRepository.getUserContact(uuid).getContactByType().size());
        assertTrue(readRepository.getUserContact(uuid).getContactByType().get("home").containsAll(updateUserCommand.getContacts()));
        assertTrue(readRepository.getUserAddress(uuid).getAddressByRegion().get("hk").containsAll(updateUserCommand.getAddresses()));
    }

    @Test
    public void should_getUserContactByContactType_givenQueryCommand() {
        var uuid = UUID.randomUUID().toString();
        var createUserCommand = CreateUserCommand.builder().userId(uuid)
                .firstName("kelvin")
                .lastName("liang")
                .build();
        var user = userAggregate.handleCreatedUserCommand(createUserCommand);
        projector.project(user);

        var updateUserCommand = UpdateUserCommand.builder().userId(uuid)
                .addresses(new HashSet<>())
                .contacts(new HashSet<>())
                .build();
        updateUserCommand.getAddresses().add(Address.builder().city("nt").state("hk").build());
        updateUserCommand.getContacts().add(Contact.builder().type("home").detail("1234").build());
        user = userAggregate.handleUpdateUserCommand(updateUserCommand);
        projector.project(user);

        var queryCommand = ContactByTypeQuery.builder().userId(uuid).contactType("home").build();
        var rs = userProjection.handle(queryCommand);

        assertEquals(1, rs.size());
        assertTrue(rs.containsAll(updateUserCommand.getContacts()));
    }

    @Test
    public void should_getUserAddressByRegion_givenQueryCommand() {
        var uuid = UUID.randomUUID().toString();
        var createUserCommand = CreateUserCommand.builder().userId(uuid)
                .firstName("kelvin")
                .lastName("liang")
                .build();
        var user = userAggregate.handleCreatedUserCommand(createUserCommand);
        projector.project(user);

        var updateUserCommand = UpdateUserCommand.builder().userId(uuid)
                .addresses(new HashSet<>())
                .contacts(new HashSet<>())
                .build();
        updateUserCommand.getAddresses().add(Address.builder().city("nt").state("hk").build());
        updateUserCommand.getContacts().add(Contact.builder().type("home").detail("1234").build());
        user = userAggregate.handleUpdateUserCommand(updateUserCommand);
        projector.project(user);

        var queryCommand = AddressByRegionQuery.builder().userId(uuid).city("hk").build();
        var rs = userProjection.handle(queryCommand);

        assertEquals(1, rs.size());
        assertTrue(rs.containsAll(updateUserCommand.getAddresses()));
    }
}
