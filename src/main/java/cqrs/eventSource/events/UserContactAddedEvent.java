package cqrs.eventSource.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserContactAddedEvent extends Event {
    private String contactType;
    private String contactDetails;
}
