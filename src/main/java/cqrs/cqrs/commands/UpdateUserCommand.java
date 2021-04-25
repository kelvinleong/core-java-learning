package cqrs.cqrs.commands;

import cqrs.domain.Address;
import cqrs.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class UpdateUserCommand {
    private String userId;
    private Set<Address> addresses = new HashSet<>();
    private Set<Contact> contacts = new HashSet<>();
}
