package edu.aynu.project.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProjectVO {

    private Integer id;
    private String name;
    private Integer teamId;
    private Integer statusId;
    private String team;
    private String teacher;
    private String students;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String note;
    private String picture;
    private Integer teacherAdmit;
    private Integer adminAdmit;
    private String teacherNote;
    private String adminNote;

}
