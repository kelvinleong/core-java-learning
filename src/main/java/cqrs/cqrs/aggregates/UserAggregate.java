package cqrs.cqrs.aggregates;

import cqrs.cqrs.commands.CreateUserCommand;
import cqrs.cqrs.commands.UpdateUserCommand;
import cqrs.cqrs.repository.UserWriteRepository;
import cqrs.domain.User;

public class UserAggregate {
    private UserWriteRepository writeRepository;

    public UserAggregate(UserWriteRepository repository) {
        this.writeRepository = repository;
    }

    public User handleCreatedUserCommand(CreateUserCommand command) {
        User user = new User(command.getUserId(), command.getFirstName(), command.getLastName());
        writeRepository.addUser(user.getUserId(), user);
        return user;
    }

    public User handleUpdateUserCommand(UpdateUserCommand command) {
        User user = writeRepository.getUser(command.getUserId());
        user.setAddresses(command.getAddresses());
        user.setContacts(command.getContacts());
        writeRepository.addUser(command.getUserId(), user);
        return user;
    }
}
