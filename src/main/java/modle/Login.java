package modle;

import lombok.*;

@NoArgsConstructor
@Setter
@Data
@AllArgsConstructor
@ToString

public class Login {

    private  String id;
    private  String username;
    private  String email;
    private  String password;
}
