package tim2.cataloging.tim2.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ActivationRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String message;

    @Column
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private STATUS status;

    @OneToOne
    private User user;
}
