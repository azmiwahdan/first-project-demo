package com.exalt.demo.soap;

import io.spring.guides.gs_producing_web_service.GetStudentByIdRequest;
import io.spring.guides.gs_producing_web_service.GetStudentByIdResponse;
import io.spring.guides.gs_producing_web_service.GetStudentRequest;
import io.spring.guides.gs_producing_web_service.GetStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private StudentRepository studentRepository;

    @Autowired
    public StudentEndpoint(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
        GetStudentResponse response = new GetStudentResponse();
        response.setStudent(studentRepository.findStudent(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByIdRequest")
    @ResponsePayload
    public GetStudentByIdResponse getStudentById(@RequestPayload GetStudentByIdRequest request) {
        GetStudentByIdResponse response = new GetStudentByIdResponse();
        response.setStudent(studentRepository.findStudentById(request.getId()));
        return response;
    }
}
