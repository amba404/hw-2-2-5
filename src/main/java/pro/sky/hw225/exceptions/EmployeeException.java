package pro.sky.hw225.exceptions;

/*
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
данная аннотация срабатывает только при дефолтной обработке исключений.
Если обрабатываются в контроллере, приходится явно указывать статускод в методе обработчика.
И зачем ResponseStatus тогда тут нужен? Нипанятнаа
*/
public class EmployeeException extends RuntimeException {
    public EmployeeException(String message) {
        super(message);
    }
}
