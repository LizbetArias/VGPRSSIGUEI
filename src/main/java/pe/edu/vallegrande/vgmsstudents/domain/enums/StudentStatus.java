package pe.edu.vallegrande.vgmsstudents.domain.enums;

public enum StudentStatus {
    ACTIVE("A", "Activo"),
    INACTIVE("I", "Inactivo"),
    TRANSFERRED("T", "Transferido"),
    GRADUATED("G", "Graduado");

    private final String code;
    private final String description;

    StudentStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
