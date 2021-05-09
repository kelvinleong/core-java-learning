package cqrs.eventSource.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserContactRemovedEvent extends Event {
    private String contactType;
    private String contactDetails;
}
