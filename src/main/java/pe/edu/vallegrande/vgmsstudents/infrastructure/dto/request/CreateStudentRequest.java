package pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.DevelopmentInfo;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.Guardian;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.HealthInfo;
import pe.edu.vallegrande.vgmsstudents.domain.model.vo.PersonalInfo;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

     private String cui;
     private PersonalInfo personalInfo;

     @JsonFormat(pattern = "dd/MM/yyyy")
     private LocalDate dateOfBirth;

     private String address;
     private String photoPerfil;
     private String institutionId;
     private String classroomId;
     private DevelopmentInfo developmentInfo;
     private List<Guardian> guardians;
     private HealthInfo healthInfo;
}
