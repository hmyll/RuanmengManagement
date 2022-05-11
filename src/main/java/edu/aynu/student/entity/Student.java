package edu.aynu.student.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Student {

    private Integer id;
    private Integer stuId;
    private String name;
    private String gender;
    private Integer age;
    private Integer teamId;
    private String classname;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;
    private String note;

}
