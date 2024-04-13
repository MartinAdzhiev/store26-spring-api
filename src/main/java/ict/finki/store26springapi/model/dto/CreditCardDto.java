package ict.finki.store26springapi.model.dto;

import lombok.Data;

@Data
public class CreditCardDto {

    private String cardNumber;

    private int cvv;

    private String holderName;
}
