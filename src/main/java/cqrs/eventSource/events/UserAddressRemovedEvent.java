package cqrs.eventSource.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddressRemovedEvent extends Event {
    private String city;
    private String state;
    private String postCode;
}
