package pe.edu.vallegrande.vgmsstudents.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vgmsstudents.application.service.StudentService;
import pe.edu.vallegrande.vgmsstudents.domain.model.Student;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.ApiResponse;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request.CreateStudentRequest;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request.UpdateStudentRequest;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class StudentRest {

    @Autowired
    private StudentService studentService;

    /**
     * Obtener todos los estudiantes
     */
    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<Student>>>> getAllStudents() {
        return studentService.getAllStudents()
                .collectList()
                .map(students -> {
                    ApiResponse<List<Student>> response = ApiResponse.success(
                            "Estudiantes obtenidos exitosamente",
                            students);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<List<Student>> response = ApiResponse.error(
                            "Error al obtener estudiantes: " + error.getMessage(),
                            "/api/students");
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
                });
    }

    /**
     * Crear un nuevo estudiante
     */
    @PostMapping
    public Mono<ResponseEntity<ApiResponse<Student>>> createStudent(@RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request)
                .map(student -> {
                    ApiResponse<Student> response = ApiResponse.success(
                            "Estudiante creado exitosamente",
                            student);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<Student> response = ApiResponse.error(
                            "Error al crear estudiante: " + error.getMessage(),
                            "/api/students");
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response));
                });
    }

    /**
     * Obtener estudiante por ID
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Student>>> getStudentById(@PathVariable String id) {
        return studentService.findById(id)
                .map(student -> {
                    ApiResponse<Student> response = ApiResponse.success(
                            "Estudiante encontrado",
                            student);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<Student> response = ApiResponse.error(
                            "Estudiante no encontrado: " + error.getMessage(),
                            "/api/students/" + id);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
                });
    }

    /**
     * Actualizar estudiante
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Student>>> updateStudent(
            @PathVariable String id,
            @RequestBody UpdateStudentRequest request) {
        return studentService.updateStudent(id, request)
                .map(student -> {
                    ApiResponse<Student> response = ApiResponse.success(
                            "Estudiante actualizado exitosamente",
                            student);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<Student> response = ApiResponse.error(
                            "Error al actualizar estudiante: " + error.getMessage(),
                            "/api/students/" + id);
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response));
                });
    }

    /**
     * Buscar estudiante por CUI
     */
    @GetMapping("/cui/{cui}")
    public Mono<ResponseEntity<ApiResponse<Student>>> getStudentByCui(@PathVariable String cui) {
        return studentService.findByCui(cui)
                .map(student -> {
                    ApiResponse<Student> response = ApiResponse.success(
                            "Estudiante encontrado por CUI",
                            student);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<Student> response = ApiResponse.error(
                            "Estudiante no encontrado con CUI: " + error.getMessage(),
                            "/api/students/cui/" + cui);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
                });
    }

    /**
     * Obtener estudiantes por aula
     */
    @GetMapping("/classroom/{classroomId}")
    public Mono<ResponseEntity<ApiResponse<List<Student>>>> getStudentsByClassroom(@PathVariable String classroomId) {
        return studentService.findByClassroom(classroomId)
                .collectList()
                .map(students -> {
                    ApiResponse<List<Student>> response = ApiResponse.success(
                            "Estudiantes del aula obtenidos exitosamente",
                            students);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<List<Student>> response = ApiResponse.error(
                            "Error al obtener estudiantes del aula: " + error.getMessage(),
                            "/api/students/classroom/" + classroomId);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
                });
    }

    /**
     * Obtener estudiantes por institución
     */
    @GetMapping("/institution/{institutionId}")
    public Mono<ResponseEntity<ApiResponse<List<Student>>>> getStudentsByInstitution(
            @PathVariable String institutionId) {
        return studentService.findByInstitution(institutionId)
                .collectList()
                .map(students -> {
                    ApiResponse<List<Student>> response = ApiResponse.success(
                            "Estudiantes de la institución obtenidos exitosamente",
                            students);
                    return ResponseEntity.ok(response);
                })
                .onErrorResume(error -> {
                    ApiResponse<List<Student>> response = ApiResponse.error(
                            "Error al obtener estudiantes de la institución: " + error.getMessage(),
                            "/api/students/institution/" + institutionId);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
                });
    }
}
