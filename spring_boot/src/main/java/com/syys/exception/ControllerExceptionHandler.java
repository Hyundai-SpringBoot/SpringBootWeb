package com.syys.exception;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.log4j.Log4j2;

 
@Component
@ControllerAdvice
@Log4j2

public class ControllerExceptionHandler {
	@ExceptionHandler
    public void handleOtherException(Exception e,
            HttpServletResponse response) throws IOException {
        log.info("handleOtherException: "+e.getMessage());
        e.printStackTrace();
        //내부 서버 에러
        response.sendError(500);
    }//end handleOtherException
   
    @ExceptionHandler
    public void handleOtherException(BadCredentialsException e,
            HttpServletResponse response) throws IOException {
        log.info("BadCredentialsException : " +e.getMessage());
        //401 권한 없음
        response.sendError(401);
    }

}
