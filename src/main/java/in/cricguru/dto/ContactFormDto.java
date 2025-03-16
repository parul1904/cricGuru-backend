package in.cricguru.dto;

import lombok.Data;

@Data
public class ContactFormDto {
    private String name;
    private String email;
    private String subject;
    private String message;
}