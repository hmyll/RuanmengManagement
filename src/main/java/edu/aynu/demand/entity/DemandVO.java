package edu.aynu.demand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DemandVO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer teamId;
    private Integer statusId;
    private String team;
    private String user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date requestTime;
    private String request;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date responseTime;
    private String response;

}
