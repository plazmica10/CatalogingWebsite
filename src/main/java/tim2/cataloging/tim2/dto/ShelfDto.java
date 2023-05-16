package tim2.cataloging.tim2.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import tim2.cataloging.tim2.model.Shelf;
import tim2.cataloging.tim2.model.ShelfItem;

import java.util.List;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ShelfDto {
    private Long id;

    private String name;

    private boolean isPrimary;

    private List<ShelfItem> shelfItems;

    public ShelfDto() {
    }

    public ShelfDto(Long id, String name, boolean isPrimary, List<ShelfItem> shelfItems) {
        this.id = id;
        this.name = name;
        this.isPrimary = isPrimary;
        this.shelfItems = shelfItems;
    }
    public ShelfDto(Shelf shelf){
        this.id = shelf.getId();
        this.name = shelf.getName();
        this.isPrimary = shelf.isPrimary();
        this.shelfItems = shelf.getShelfItems();
    }
}
