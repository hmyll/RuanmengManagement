package edu.aynu.team.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TeamVO {

    private Integer id;
    private String name;
    private Integer teacherId;
    private Integer statusId;
    private Integer captionId;
    private String teacher;
    private String caption;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date setup;
    private Integer stuCount;
    private Integer proCount;
    private String description;
    private Integer teacherAdmit;
    private Integer adminAdmit;
    private String Students;
    private String appForm;

}
