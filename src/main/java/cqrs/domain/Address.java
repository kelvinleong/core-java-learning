package cqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Address {
    private String city;
    private String state;
    private String postCode;
}
