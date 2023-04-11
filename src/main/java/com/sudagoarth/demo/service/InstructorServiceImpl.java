package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.InstructorRepository;
import com.sudagoarth.demo.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements IInstructorService {

    private final InstructorRepository instructorRepository;
    public InstructorServiceImpl(InstructorRepository theInstructorRepository) {
        instructorRepository = theInstructorRepository;
    }


    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(int theId) {
        Optional<Instructor> result = instructorRepository.findById(theId);
        Instructor theInstructor = null;
        if (result.isPresent()) {
            theInstructor = result.get();
        }
        return theInstructor;
    }

    @Override
    public Instructor save(Instructor theInstructor) {
        return instructorRepository.save(theInstructor);
    }

    @Override
    public void deleteById(int theId) {
        instructorRepository.deleteById(theId);
    }

}
