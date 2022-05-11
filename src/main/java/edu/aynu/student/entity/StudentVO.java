package edu.aynu.student.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StudentVO {

    private Integer id;
    private Integer stuId;
    private String name;
    private String gender;
    private Integer age;
    private String team;
    private String classname;
    private Integer proCount;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;
    private String note;

}
