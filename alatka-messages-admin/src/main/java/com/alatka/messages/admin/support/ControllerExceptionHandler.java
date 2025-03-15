package com.alatka.messages.admin.support;

import com.alatka.messages.admin.model.ResMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component("messagesControllerExceptionHandler")
@RestControllerAdvice(basePackages = "com.alatka.messages.admin")
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 处理@RequestBody/@ModelAttribute标记的参数@Valid校验错误抛出的异常
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResMessage<Void> handArgBindException(Exception e) {
        logger.error(e.getMessage(), e);
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            bindingResult = ex.getBindingResult();
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            bindingResult = ex.getBindingResult();
        }
        String detail = bindingResult.getAllErrors().get(0).getDefaultMessage();
        return ResMessage.error(detail);
    }

    /**
     * 处理service层异常
     */
    @ExceptionHandler(Exception.class)
    public ResMessage<Void> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResMessage.error(e.getMessage());
    }

}
