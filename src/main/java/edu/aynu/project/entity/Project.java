package edu.aynu.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Project {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer teamId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String note;
    private String picture;
    private Integer teacherAdmit;
    private Integer adminAdmit;
    private String teacherNote;
    private String adminNote;
}
