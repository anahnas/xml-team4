package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.Transmission;

@Getter
@Setter
@NoArgsConstructor
public class TransmissionDTOh {
    private Long id;
    private String name;

    public TransmissionDTOh(Transmission transmission) {
        this.id = transmission.getId();
        this.name = transmission.getName();
    }
}
