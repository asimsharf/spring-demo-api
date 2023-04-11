package com.sudagoarth.demo.service;

import com.sudagoarth.demo.dao.IStudentRepository;
import com.sudagoarth.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements IStudentService {

	private final IStudentRepository studentRepository;
	
	public StudentServiceImpl(IStudentRepository theIStudentRepository) {
		studentRepository = theIStudentRepository;
	}
	
	@Override
	public List<Student> findAll() {
		return studentRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Student findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);
		Student theStudent = null;
		if (result.isPresent()) {
			theStudent = result.get();
		}
		return theStudent;
	}

	@Override
	public Student save(Student theStudent) {
		studentRepository.save(theStudent);
		return theStudent;
	}

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);
	}

}






