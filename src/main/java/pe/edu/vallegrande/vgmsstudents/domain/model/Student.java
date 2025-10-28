package pe.edu.vallegrande.vgmsstudents.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.vallegrande.vgmsstudents.domain.enums.StudentStatus;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.DevelopmentInfo;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.Guardian;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.HealthInfo;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.PersonalInfo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "students")
public class Student {

    @Id
    private String studentId;

    private String cui;

    private PersonalInfo personalInfo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private String address;

    private String photoPerfil;

    private char status;

    // referencia a institucion y a classrooms
    private String institutionId;
    private String classroomId;

    //referencia a objetos de valor
    private DevelopmentInfo developmentInfo;
    private List<Guardian> guardians;
    private HealthInfo healthInfo;

}
