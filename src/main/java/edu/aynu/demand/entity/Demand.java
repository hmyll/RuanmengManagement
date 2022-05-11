package edu.aynu.demand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Demand {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer teamId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date requestTime;
    private String request;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date responseTime;
    private String response;

}
