package com.weatherbroker.exeption;


import com.weatherbroker.view.NegativeResponseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(MainExceptionHandler.class);

    /**
     * Ообработка исключений на этапе валидации
     * @param ex - исключение
     * @param headers -заголовок HTTP ответа
     * @param status - статус HTTP ответа
     * @param request - HTTP запрос
     * @return - возврат объекта, в котором содержится обработанное сообщение об ошибке
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex.getCause());
        StringBuilder sb = new StringBuilder();
        for (ObjectError oe : ex.getBindingResult().getAllErrors()) {
            sb.append(oe.getDefaultMessage());
            sb.append("  ");
        }
        return new ResponseEntity<>(new NegativeResponseView("При валидации возникла ошибка: " + sb.toString()), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param e - исключения
     * @return - возврат объект, в котором содержится обработанное сообщение об ошибке
     */
    @ExceptionHandler({CustomOrganizationException.class})
    protected @ResponseBody
    ResponseEntity<?> catcherAllCustomExceptions(Exception e) {
        log.error(e.getMessage(), e.getCause());
        return new ResponseEntity<>(new NegativeResponseView(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex - Исключения
     * @return - возврат объект, в котором содержится обработанное сообщение об ошибке
     */
    @ExceptionHandler({Exception.class})
    protected @ResponseBody ResponseEntity<?> catcherAllExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new NegativeResponseView ("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
