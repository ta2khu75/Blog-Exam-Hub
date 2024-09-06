package com.ta2khu75.quiz.service;
public interface CrudService <Id, Request, Response>{
    Response create(Request request);
    Response update(Id id,Request request);
    Response read(Id id);
    void delete(Id id);
}