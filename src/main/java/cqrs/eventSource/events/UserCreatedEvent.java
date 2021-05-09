package cqrs.eventSource.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public class UserCreatedEvent extends Event {
    private String userId;
    private String firstName;
    private String lastName;
}
