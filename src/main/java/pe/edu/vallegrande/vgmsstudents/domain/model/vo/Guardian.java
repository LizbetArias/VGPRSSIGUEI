package pe.edu.vallegrande.vgmsstudents.domain.model.vo;

import lombok.Data;

@Data
public class Guardian {
    private String relationship;
    private String names;
    private String lastNames;
    private String phone;
    private String documentType;
    private String documentNumber;
    private String userId;
}