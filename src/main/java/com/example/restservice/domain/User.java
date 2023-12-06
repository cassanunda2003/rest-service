package com.example.restservice.domain;

import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String ip_address;
    private Float latitude;
    private Float longitude;

}
