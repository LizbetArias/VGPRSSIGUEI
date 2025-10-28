package pe.edu.vallegrande.vgmsstudents.domain.model.vo;

import lombok.Data;

@Data
public class DevelopmentInfo {
    private String birthType; // normal / cesarea
    private String complications; // descripción si existió

    private boolean hasAuditoryDisability;
    private boolean hasVisualDisability;
    private boolean hasMotorDisability;
    private String otherDisability;

    // Hitos del desarrollo (valores aproximados tipo "3 meses", "2 años", etc.)
    private String liftedHeadAt;
    private String satAt;
    private String crawledAt;
    private String stoodUpAt;
    private String walkedAt;
    private String controlledSphinctersAt;
    private String spokeFirstWordsAt;
    private String spokeFluentlyAt;
}
