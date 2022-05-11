package edu.aynu.teacher.entity;

import lombok.Data;

@Data
public class TeacherVO {

    private Integer id;
    private String name;
    private String gender;
    private String teacherStatus;
    private String title;
    private String education;
    private String field;
    private String phone;
    private Integer adminAdmit;
    private String adminNote;
    private String teams;
}
