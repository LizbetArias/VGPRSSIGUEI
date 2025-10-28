package pe.edu.vallegrande.vgmsstudents.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vgmsstudents.application.service.StudentService;
import pe.edu.vallegrande.vgmsstudents.domain.model.Student;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request.CreateStudentRequest;
import pe.edu.vallegrande.vgmsstudents.infrastructure.dto.request.UpdateStudentRequest;
import pe.edu.vallegrande.vgmsstudents.infrastructure.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> createStudent(CreateStudentRequest request) {
        Student student = Student.builder()
                .cui(request.getCui())
                .personalInfo(request.getPersonalInfo())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .photoPerfil(request.getPhotoPerfil())
                .status('A') // Activo por defecto
                .institutionId(request.getInstitutionId())
                .classroomId(request.getClassroomId())
                .developmentInfo(request.getDevelopmentInfo())
                .guardians(request.getGuardians())
                .healthInfo(request.getHealthInfo())
                .build();

        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> updateStudent(String id, UpdateStudentRequest request) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    // Actualizar solo los campos que no son null
                    if (request.getPersonalInfo() != null) {
                        existingStudent.setPersonalInfo(request.getPersonalInfo());
                    }
                    if (request.getDateOfBirth() != null) {
                        existingStudent.setDateOfBirth(request.getDateOfBirth());
                    }
                    if (request.getAddress() != null) {
                        existingStudent.setAddress(request.getAddress());
                    }
                    if (request.getPhotoPerfil() != null) {
                        existingStudent.setPhotoPerfil(request.getPhotoPerfil());
                    }
                    if (request.getClassroomId() != null) {
                        existingStudent.setClassroomId(request.getClassroomId());
                    }
                    if (request.getDevelopmentInfo() != null) {
                        existingStudent.setDevelopmentInfo(request.getDevelopmentInfo());
                    }
                    if (request.getGuardians() != null) {
                        existingStudent.setGuardians(request.getGuardians());
                    }
                    if (request.getHealthInfo() != null) {
                        existingStudent.setHealthInfo(request.getHealthInfo());
                    }
                    if (request.getStatus() != 0) {
                        existingStudent.setStatus(request.getStatus());
                    }

                    return studentRepository.save(existingStudent);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Estudiante no encontrado con ID: " + id)));
    }

    @Override
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Estudiante no encontrado con ID: " + id)));
    }

    @Override
    public Mono<Student> findByCui(String cui) {
        return studentRepository.findByCui(cui)
                .switchIfEmpty(Mono.error(new RuntimeException("Estudiante no encontrado con CUI: " + cui)));
    }

    @Override
    public Flux<Student> findByClassroom(String classroomId) {
        return studentRepository.findByClassroomId(classroomId);
    }

    @Override
    public Flux<Student> findByInstitution(String institutionId) {
        return studentRepository.findByInstitutionId(institutionId);
    }
}
