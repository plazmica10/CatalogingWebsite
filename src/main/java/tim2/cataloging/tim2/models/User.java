package tim2.cataloging.tim2.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private Date date;

    @Column
    private String photo;   //link to photo

    @Column
    private String description;
    private enum Role {READER,AUTHOR,ADMIN}

    @Column
    private Role role;

    //    public boolean isActive(){
//        if(this.role == Author){
//          if(Aktivacija.status == odobren){
//              return true;
//          }else{
//              return false;
//          }
//        }
//    }
}
