package com.endsoul.instructor.course;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true) // 읽기 전용 트랜잭션이 시작된 이후 INSERT, UPDATE, DELETE 같은 쓰기 작업이 진행되면 예외가 발생
public interface CourseRepository extends Repository<Course, Long> {
  Seq<Course> findAll();

  Option<Course> findById(Long id);

  // 데이터베이스 쓰기 작업을 하는 메소드에는 @Transactional @Modifying 붙여준다.
  @Transactional
  @Modifying
  Course save(Course course);

  @Transactional
  @Modifying
  void delete(Course course);
}
