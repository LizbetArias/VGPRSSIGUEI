package pe.edu.vallegrande.vgmsstudents.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HealthControl {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private double weight; // kg
    private double height; // cm

    public double getBMI() {
        return weight / Math.pow(height / 100, 2);
    }
}
