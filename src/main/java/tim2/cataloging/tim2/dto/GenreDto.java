package tim2.cataloging.tim2.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import tim2.cataloging.tim2.model.Genre;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GenreDto {

        private Long id;
        private String name;

        public GenreDto() {
        }

        public GenreDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public GenreDto(Genre genre) {
            this.id = genre.getId();
            this.name = genre.getName();
        }

}
