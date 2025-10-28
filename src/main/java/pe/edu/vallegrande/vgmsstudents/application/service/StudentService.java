package pe.edu.vallegrande.vgmsstudents.application.service;

import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vgmsstudents.domain.model.Student;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request.CreateStudentRequest;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request.UpdateStudentRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface StudentService {

    Flux<Student> getAllStudents();

    Mono<Student> createStudent(CreateStudentRequest request);

    Mono<Student> updateStudent(String id, UpdateStudentRequest request);

    Mono<Student> findById(String id);

    Mono<Student> findByCui(String cui);

    Flux<Student> findByClassroom(String classroomId);

    Flux<Student> findByInstitution(String institutionId);
}
