package com.example.excelwithapachepoi.service;

public interface ServiceGeneration<T> {
   T get(String code);

   T save(T t);

   void delete(String code);
}
