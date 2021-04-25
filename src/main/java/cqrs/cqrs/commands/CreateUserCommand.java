package cqrs.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateUserCommand {
    private String userId;
    private String firstName;
    private String lastName;
}
