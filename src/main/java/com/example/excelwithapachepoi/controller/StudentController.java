package com.example.excelwithapachepoi.controller;

import com.example.excelwithapachepoi.entity.Option;
import com.example.excelwithapachepoi.service.OptionServiceImpl;
import com.example.excelwithapachepoi.util.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final OptionServiceImpl studentService;

    @PostMapping
    public ResponseEntity<List<Option>> saveStudent(@RequestBody Request url) {
        String urlString = url.getData().toString();
        return new ResponseEntity<>(studentService.saveAllExcel(urlString), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getAllExcel() {
        return new ResponseEntity<>(studentService.getAllExcel(), HttpStatus.OK);
    }
}
