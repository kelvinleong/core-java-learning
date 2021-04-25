package cqrs.cqrs.projectors;

import cqrs.cqrs.repository.UserReadRepository;
import cqrs.domain.User;
import cqrs.domain.UserAddress;
import cqrs.domain.UserContact;

import java.util.HashSet;
import java.util.Optional;

public class UserProjector {
    UserReadRepository userReadRepository;

    public UserProjector(UserReadRepository userReadRepository) {
        this.userReadRepository = userReadRepository;
    }

    public void project(User user) {
        var userContact = userReadRepository.getUserContact(user.getUserId());
        Optional.ofNullable(userContact)
                .map(u -> {
                    user.getContacts()
                            .forEach(contact -> u.getContactByType()
                                    .compute(contact.getType(), (k, v) -> v == null ? new HashSet<>() : v)
                                    .add(contact));
                    return Optional.empty();
                })
                .or(() -> {
                    userReadRepository.addUserContact(user.getUserId(), new UserContact());
                    return Optional.empty();
                });

        var userAddress = userReadRepository.getUserAddress(user.getUserId());
        Optional.ofNullable(userAddress)
                .map(u -> {
                    user.getAddresses()
                            .forEach(address -> u.getAddressByRegion()
                                    .compute(address.getState(), (k, v) -> v == null ? new HashSet<>() : v)
                                    .add(address));
                    return Optional.empty();
                })
                .or(() -> {
                    userReadRepository.addUserAddress(user.getUserId(), new UserAddress());
                    return Optional.empty();
                });
    }
}
