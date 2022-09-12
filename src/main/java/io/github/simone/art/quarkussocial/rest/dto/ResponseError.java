package io.github.simone.art.quarkussocial.rest.dto;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//Classe que representa o objeto de retorno(Response)
public class ResponseError {

    private String message;
    private Collection<FieldError> errors;

    public ResponseError(String message, Collection<FieldError> errors) {
        super();
        this.message = message;
        this.errors = errors;
    }

    //<T> o t é de genérico e ele valida o erro de respostas baseado nas violações
    // T é de qualquer objeto
    public static <T> ResponseError createFromValidation(Set<ConstraintViolation<T>> violations) {
//        O map mapea cada um dos objetos
        // Capturará cada FieldError que foi gerado dessas violations e colocará em uma lista
        List<FieldError> errors = violations
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                //collect(Collectors.toList()); vai ser pego cada erro como mensagem de uma lista
                .collect(Collectors.toList());

        String message = "Validation Error";
        var responseError = new ResponseError(message, errors);
                return responseError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Collection<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<FieldError> errors) {
        this.errors = errors;
    }
}
