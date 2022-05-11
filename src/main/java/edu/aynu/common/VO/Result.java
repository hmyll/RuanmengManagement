package edu.aynu.common.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;

    public static Result<Object> success(){
        return  new Result(0,"success",null,null);
    }
    public static Result<Object> success(String msg){
        return  new Result(0,msg,null,null);
    }
    public static Result<Object> fail(){
        return  new Result(-1,"fail",null,null);
    }
    public static Result<Object> fail(String msg){
        return  new Result(-1,msg,null,null);
    }
    public static Result<Object> examTeacher(String name){
        return  new Result(2,name,null,null);
    }
    public static Result<Object> examTeam(String name){
        return  new Result(3,name,null,null);
    }

}
