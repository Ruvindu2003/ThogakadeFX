package Orders;

import lombok.*;

@ToString
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private  String id;
    private  String date;
    private String  itemid;
}
