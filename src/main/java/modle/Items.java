package modle;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Items {

    private String code;
    private String description;
    private double unitprice;
    private int qtyonHand;


}
