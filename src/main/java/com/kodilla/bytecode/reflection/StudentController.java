package com.kodilla.bytecode.reflection;

import org.openjdk.jol.vm.VM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @PostMapping
    public ResponseEntity<Object> getStudentMap(@RequestBody Request request) throws NoSuchFieldException, IllegalAccessException {
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
}