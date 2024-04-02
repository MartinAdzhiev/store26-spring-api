package ict.finki.store26springapi.model.dto;

import lombok.Data;

@Data
public class LogInRequest {

    private String email;
    private String password;
}
