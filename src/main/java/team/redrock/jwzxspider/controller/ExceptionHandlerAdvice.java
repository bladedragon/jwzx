package team.redrock.jwzxspider.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import team.redrock.jwzxspider.Exception.StuidValidException;
import team.redrock.jwzxspider.utils.response.ErrorResponse;


@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    @ExceptionHandler(StuidValidException.class)
    public ErrorResponse handleException(StuidValidException e) {
        e.printStackTrace();
        return new ErrorResponse(415,e.getMessage());
    }
}
