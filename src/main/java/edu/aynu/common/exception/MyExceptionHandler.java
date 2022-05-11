package edu.aynu.common.exception;

import edu.aynu.common.VO.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> myHandler(Exception e){
        e.printStackTrace();
        return Result.fail("系统错误"+e.getMessage());
    }
}
