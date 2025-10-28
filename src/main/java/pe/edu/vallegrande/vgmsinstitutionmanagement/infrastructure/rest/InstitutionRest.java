package pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vgmsinstitutionmanagement.application.service.InstitutionService;
import pe.edu.vallegrande.vgmsinstitutionmanagement.domain.model.Institution;
import pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.dto.request.InstitutionCreateWithUsersRequest;
import pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.dto.request.InstitutionUpdateRequest;
import pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.dto.response.InstitutionCompleteResponseDto;
import pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.dto.response.InstitutionWithUsersAndClassroomsResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/institutions")
@RequiredArgsConstructor
@Tag(name = "Instituciones", description = "API para gestión de instituciones educativas")
public class InstitutionRest {

    private final InstitutionService service;

    @GetMapping
    @Operation(summary = "Listar todas las instituciones", description = "Obtiene todas las instituciones con información completa de aulas")
    public Flux<InstitutionCompleteResponseDto> listarTodos() {
        return service.listAllComplete();
    }

    @GetMapping("/active")
    @Operation(summary = "Listar instituciones activas", description = "Obtiene todas las instituciones con estado activo")
    public Flux<InstitutionCompleteResponseDto> listarActivos() {
        return service.listActiveComplete();
    }

    @GetMapping("/inactive")
    @Operation(summary = "Listar instituciones inactivas", description = "Obtiene todas las instituciones con estado inactivo o eliminadas")
    public Flux<InstitutionCompleteResponseDto> listarInactivos() {
        return service.listInactiveComplete();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener institución por ID", description = "Obtiene una institución específica con información completa de aulas, director y auxiliares")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Institución encontrada"),
            @ApiResponse(responseCode = "404", description = "Institución no encontrada")
    })
    public Mono<InstitutionWithUsersAndClassroomsResponseDto> obtenerPorId(
            @Parameter(description = "ID de la institución") @PathVariable String id) {
        return service.getCompleteWithUsers(id);
    }

    @PostMapping("/with-users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear institución con usuarios", description = "Crea una institución y registra automáticamente director y auxiliares")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Institución y usuarios creados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public Mono<Institution> crearConUsuarios(@Valid @RequestBody InstitutionCreateWithUsersRequest request) {
        return service.createWithUsers(request);
    }

    @GetMapping("/with-users-classrooms")
    @Operation(summary = "Listar instituciones completas", description = "Obtiene todas las instituciones con información completa de usuarios (director y auxiliares) y aulas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    public Flux<InstitutionWithUsersAndClassroomsResponseDto> listarTodosConUsuariosYClassrooms() {
        return service.listAllWithUsersAndClassrooms();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar institución", description = "Actualiza la información completa de una institución existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Institución actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Institución no encontrada")
    })
    public Mono<Institution> actualizar(
            @Parameter(description = "ID de la institución a actualizar") @PathVariable String id,
            @Valid @RequestBody InstitutionUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar institución (lógico)", description = "Elimina lógicamente una institución marcándola como inactiva y estableciendo fecha de eliminación")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Institución eliminada lógicamente"),
            @ApiResponse(responseCode = "400", description = "La institución ya está eliminada"),
            @ApiResponse(responseCode = "404", description = "Institución no encontrada")
    })
    public Mono<Institution> eliminarLogico(
            @Parameter(description = "ID de la institución a eliminar") @PathVariable String id) {
        return service.deleteLogical(id);
    }

    @PutMapping("/{id}/restore")
    @Operation(summary = "Restaurar institución", description = "Restaura una institución previamente eliminada lógicamente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Institución restaurada exitosamente"),
            @ApiResponse(responseCode = "400", description = "La institución no está eliminada"),
            @ApiResponse(responseCode = "404", description = "Institución no encontrada")
    })
    public Mono<Institution> restaurar(
            @Parameter(description = "ID de la institución a restaurar") @PathVariable String id) {
        return service.restore(id);
    }
}
