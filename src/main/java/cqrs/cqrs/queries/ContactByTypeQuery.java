package cqrs.cqrs.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ContactByTypeQuery {
    private String userId;
    private String contactType;
}
