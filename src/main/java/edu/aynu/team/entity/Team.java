package edu.aynu.team.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Team {

    private Integer id;
    private String name;
    private Integer teacherId;
    private Integer captionId;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date setup;
    private String description;
    private Integer teacherAdmit;
    private String teacherNote;
    private Integer adminAdmit;
    private String adminNote;
    private String appForm;

}
