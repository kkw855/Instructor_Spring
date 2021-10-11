package com.endsoul.instructor.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // @Entity 는 파라미터가 없는 기본 생성자가 있어야 한다
@Getter
@Setter
@Entity // 객체와 테이블 매핑
public class Course {

  @SequenceGenerator( // DB 에서 생성한 시퀀스를 맵핑
      name = "course_sequence", // 코드에서 사용할 시퀀스 이름
      sequenceName = "course_sequence", // DB 에서 생성한 시퀀스 이름
      allocationSize = 1)
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "course_sequence" // @SequenceGenerator 어노테이션의 name 값
  )
  @Id // 기본 키 매핑
  private Long id;
  private String username;
  private String description;

  public Course(String username, String description) {
    this.username = username;
    this.description = description;
  }
}
