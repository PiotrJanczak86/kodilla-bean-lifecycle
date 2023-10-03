package com.kodilla.bytecode.reflection;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.openjdk.jol.vm.VM;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @PostMapping
    public ResponseEntity<Object> getStudentMap(@Valid @RequestBody Request request) throws NoSuchFieldException, IllegalAccessException {
        Field indexField = Student.class.getDeclaredField("indexNumber");
        indexField.setAccessible(true);

        Student[] students = new Student[(int)request.getN()];
        Map<Long, String> map = new HashMap<>();

        for (int i = 0; i< request.getN(); i++){
            students[i] = new Student(request.getZ());
            map.put(VM.current().addressOf(students[i]), (String)indexField.get(students[i]));
        }
        return ResponseEntity.ok(map);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Map<String, String>> handleException(ConstraintViolationException exc) {
        Map<String, String> resultMap = new HashMap<>();
        String[] errorArray = exc.getMessage().split(":");
        resultMap.put(errorArray[0], errorArray[1]);
        return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException exc) {
        Map<String, String> errorsMap = new HashMap<>();
        List<ObjectError> errorsList = exc.getBindingResult().getAllErrors();
        errorsList.forEach((errorObject) -> {
            FieldError fieldError = (FieldError) errorObject;
            String name = fieldError.getField();
            String message = errorObject.getDefaultMessage();
            errorsMap.put(name, message);
        });
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

}