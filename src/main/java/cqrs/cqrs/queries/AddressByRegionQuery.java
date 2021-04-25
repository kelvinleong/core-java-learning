package cqrs.cqrs.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressByRegionQuery {
    private String userId;
    private String city;
}
