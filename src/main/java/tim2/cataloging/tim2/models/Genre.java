package tim2.cataloging.tim2.models;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Genre implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
}