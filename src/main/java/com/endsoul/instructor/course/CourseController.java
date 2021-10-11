package com.endsoul.instructor.course;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.*;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// @RequestBody 어노테이션과 @ResponseBody 어노테이션은 각각 HTTP 요청 몸체를
// 자바 객체로 변환하고 자바 객체를 HTTP 응답 몸체로 변환하는 데 사용된다.

@AllArgsConstructor
@RestController // @Controller + @ResponseBody
@CrossOrigin(origins = {"http://localhost:8081"}) // 명시한 URL 로 들어오는 요청을 허용 한다
public class CourseController {

  private final CourseService courseService;

  @GetMapping("/instructors/{username}/courses")
  public Seq<Course> getAllCourses(@PathVariable final String username) {
    return courseService.findAll();
  }

  @GetMapping("/instructors/{username}/courses/{id}")
  public ResponseEntity<Course> getCourse(@PathVariable final Long id) {
    final Option<Course> course = courseService.findById(id);

    // Some: 200 OK, None: 404 Not Found
    return ResponseEntity.of(course.toJavaOptional());
  }

  @DeleteMapping("/instructors/{username}/courses/{id}")
  public ResponseEntity<Course> deleteCourse(@PathVariable final String username, @PathVariable final Long id) {
    final Option<Course> course = courseService.deleteById(id);

    return Match(course).of(
        Case($Some($()), ResponseEntity.noContent().build()),
        Case($None(), ResponseEntity.notFound().build())
    );
  }
}
