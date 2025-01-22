package Orders;

import lombok.*;

@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor

public class OrderDetails {
    private String id;
    private String itemcode;
    private  int qty;
    private  double uniteprice;


}
