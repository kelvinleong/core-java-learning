package cqrs.eventSource.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddressAddedEvent extends Event {
    private String city;
    private String state;
    private String postCode;
}
