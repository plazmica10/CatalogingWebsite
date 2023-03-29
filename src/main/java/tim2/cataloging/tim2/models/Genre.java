package tim2.cataloging.tim2.models;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Genre implements Serializable{
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}