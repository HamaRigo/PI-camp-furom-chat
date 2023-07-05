package tn.esprit.tunisiacampbackend.DAO.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tunisiacampbackend.DAO.Entities.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    int phoneNumber;
    Role role;
    private List<Comment> comments;
    private List<React> reacts;
    private List<Post> posts;

//    List<OrderDTO> orders ;
//    List<FeedbackDTO> feedbackList;
}
