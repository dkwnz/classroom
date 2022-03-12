package demo.pojo;

import lombok.Data;

@Data
public class Change {
    private Long id;

    private String name;

    private Long cny_wallet;

    private Long us_wallet;
}
