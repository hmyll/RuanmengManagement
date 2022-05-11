package edu.aynu.user.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String name;
    private Integer status;
    private Integer statusId;
    private String loginName;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registerTime;

}
