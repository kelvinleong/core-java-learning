package cqrs.cqrs.projections;

import cqrs.cqrs.queries.AddressByRegionQuery;
import cqrs.cqrs.queries.ContactByTypeQuery;
import cqrs.cqrs.repository.UserReadRepository;
import cqrs.domain.Address;
import cqrs.domain.Contact;
import cqrs.domain.UserAddress;
import cqrs.domain.UserContact;


import java.util.Optional;
import java.util.Set;

public class UserProjection {
    private UserReadRepository readRepository;

    public UserProjection(UserReadRepository userReadRepository) {
        this.readRepository = userReadRepository;
    }

    public Set<Contact> handle(ContactByTypeQuery query) {
        UserContact userContact = readRepository.getUserContact(query.getUserId());
        return Optional.ofNullable(userContact)
                .map(u -> userContact.getContactByType().get(query.getContactType()))
                .orElseThrow(() -> new RuntimeException("User not existed!"));
    }

    public Set<Address> handle(AddressByRegionQuery query) {
        UserAddress userAddress = readRepository.getUserAddress(query.getUserId());
        return Optional.ofNullable(userAddress)
                .map(u -> userAddress.getAddressByRegion().get(query.getCity()))
                .orElseThrow(() -> new RuntimeException("User not existed!"));
    }
}
