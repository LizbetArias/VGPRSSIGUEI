package pe.edu.vallegrande.vgmsstudents.domain.model.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthInfo {
    private List<HealthControl> controls;
    private String healthStatus;
    private String illnesses;
    private String vaccines;

}
