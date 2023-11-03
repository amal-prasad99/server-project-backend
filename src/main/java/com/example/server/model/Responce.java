package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class Responce {
    protected LocalDateTime timeStamp;
    protected int statusCodee;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String devveloperMassage;
    protected Map<?,?> data;
}
