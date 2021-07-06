package kg.megacom.requestforstudents.models.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
public class SubscribersDto {

    private Long id;

    private String name;
    private String birthDate;
    private String number;
}
