package tim2.cataloging.tim2.model;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Genre implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;
}