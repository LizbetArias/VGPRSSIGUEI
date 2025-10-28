package pe.edu.vallegrande.vgmsstudents.infrastructure.client.dto;

import lombok.Data;

@Data
public class ClassroomDto {
    private String classroomId;
    private String classroomName;
    private String levelName;
    private String grade;
    private String section;
}
