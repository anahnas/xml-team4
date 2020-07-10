package xmlteam4.rentservice.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewForm {
    private Long rentId;
    private Double penaltyKms;
}
