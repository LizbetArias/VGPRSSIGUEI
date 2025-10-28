package pe.edu.vallegrande.vgmsstudents.domain.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import pe.edu.vallegrande.vgmsstudents.domain.enums.DocumentType;
import pe.edu.vallegrande.vgmsstudents.domain.enums.Gender;

import java.time.LocalDate;
import java.time.Period;

@Value
@Builder
public class PersonalInfo {
    String names;
    String lastNames;
    DocumentType documentType;
    String documentNumber;
    Gender gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dateOfBirth;

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
