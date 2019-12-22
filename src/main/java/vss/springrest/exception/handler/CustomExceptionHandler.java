package vss.springrest.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vss.springrest.dto.Message;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Message handleConflict(HttpServletRequest request, Exception ex){
        Message message = new Message();
        message.setMessage(ex.getMessage());
        message.setExceptionName(ex.getClass().getName());
        return message;
    }
}
