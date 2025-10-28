package pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import pe.edu.vallegrande.vgmsinstitutionmanagement.application.service.ClassroomService;
import pe.edu.vallegrande.vgmsinstitutionmanagement.domain.model.Classroom;
import pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.dto.request.ClassroomCreateRequest;
import pe.edu.vallegrande.vgmsinstitutionmanagement.infrastructure.dto.request.ClassroomUpdateRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassroomRest {

    private final ClassroomService service;

    // Listar solo activos
    @GetMapping("/active")
    @Operation(summary = "Listar aulas activas", description = "Obtiene todas las aulas con estado activo")
    public Flux<Classroom> listActive() {
        return service.listActive();
    }

    // Listar solo inactivos
    @GetMapping("/inactive")
    @Operation(summary = "Listar aulas inactivas", description = "Obtiene todas las aulas con estado inactivo")
    public Flux<Classroom> listInactive() {
        return service.listInactive();
    }

    // Listar todos (activos e inactivos)
    @GetMapping
    @Operation(summary = "Listar todas las aulas", description = "Obtiene todas las aulas con información completa")
    public Flux<Classroom> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar aula por ID", description = "Obtiene una aula específica por su ID")
    public Mono<Classroom> searchById(@PathVariable String id) {
        return service.searchById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar aula", description = "Actualiza la información de una aula existente")
    public Mono<Classroom> update(
            @PathVariable String id,
            @Valid @RequestBody ClassroomUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar aula", description = "Elimina una aula")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    @PatchMapping("/{id}/restore")
    @Operation(summary = "Restaurar aula", description = "Restaura una aula previamente eliminada")
    public Mono<Classroom> restore(@PathVariable String id) {
        return service.restore(id);
    }

    @PostMapping
    @Operation(summary = "Crear aula", description = "Crea una nueva aula")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Classroom> create(@Valid @RequestBody ClassroomCreateRequest request) {
        return service.create(request);
    }

}