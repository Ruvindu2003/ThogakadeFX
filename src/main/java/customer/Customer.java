package customer;

import lombok.*;

@NoArgsConstructor
@Getter
@Data
@Setter
@AllArgsConstructor

public class Customer {

    private String id;
    private  String name;
    private String adress;
    private  Double salary;
}
