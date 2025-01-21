package Orders;

import lombok.*;

@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
@AllArgsConstructor


public class CarTm {
    String itemcode;
    String Description;
    int qty;
    double unitprice;
    double total;


}
