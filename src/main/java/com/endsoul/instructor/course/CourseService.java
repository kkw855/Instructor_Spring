package com.endsoul.instructor.course;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Patterns.$Some;
import static io.vavr.Predicates.instanceOf;

import io.vavr.API.Match;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Option.Some;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;

    courseRepository.save(new Course("in28minutes", "Learn Full stack with Spring Boot and Angular"));
    courseRepository.save(new Course("in28minutes", "Learn Full stack with Spring Boot and React"));
    courseRepository.save(new Course("in28minutes", "Master Microservices with Spring Boot and Spring Cloud"));
    courseRepository.save(new Course("in28minutes", "Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
  }

  public Seq<Course> findAll() {
    return courseRepository.findAll();
  }

  public Option<Course> findById(final Long id) {
    return courseRepository.findById(id);
  }

  public Option<Course> deleteById(final Long id) {
    final Option<Course> course = courseRepository.findById(id);

    course.forEach(courseRepository::delete);

    return course;
  }
}
