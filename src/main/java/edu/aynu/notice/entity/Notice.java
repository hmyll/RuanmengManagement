package edu.aynu.notice.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Notice {

    private Integer id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private Integer count;
    private String content;
    private String picture;

}
