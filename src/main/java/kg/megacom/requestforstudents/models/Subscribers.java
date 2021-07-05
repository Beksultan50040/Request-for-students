package kg.megacom.requestforstudents.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Subscribers")
@Data
public class Subscribers {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Date birthDate;
    private String number;
    private int age;
}
