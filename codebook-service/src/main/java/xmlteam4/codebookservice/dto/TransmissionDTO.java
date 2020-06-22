package xmlteam4.codebookservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xmlteam4.codebookservice.model.Transmission;

@Getter
@Setter
@NoArgsConstructor
public class TransmissionDTO {
    private Long id;
    private String name;

    public TransmissionDTO(Transmission transmission) {
        this.id = transmission.getId();
        this.name = transmission.getName();
    }
}
