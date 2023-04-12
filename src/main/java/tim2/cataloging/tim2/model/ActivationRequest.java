package tim2.cataloging.tim2.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

enum Status {
    PENDING, APPROVED, DENIED
}

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

    @Enumerated(EnumType.ORDINAL) // todo fix this
    private Status status;
}
