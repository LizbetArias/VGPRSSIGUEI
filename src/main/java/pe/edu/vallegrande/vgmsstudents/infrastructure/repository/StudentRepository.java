package pe.edu.vallegrande.vgmsstudents.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.vgmsstudents.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
     Mono<Student> findByCui(String cui);

     Flux<Student> findByClassroomId(String classroomId);

     Flux<Student> findByInstitutionId(String institutionId);
}
