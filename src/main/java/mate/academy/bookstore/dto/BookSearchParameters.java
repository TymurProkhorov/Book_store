package mate.academy.bookstore.dto;

import lombok.Data;

@Data
public class BookSearchParameters {
    private String[] titles;
    private String[] authors;
    private String[] descriptions;
    private String[] prices;
}

