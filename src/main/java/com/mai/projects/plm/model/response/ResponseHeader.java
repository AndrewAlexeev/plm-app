package com.mai.projects.plm.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ResponseHeader {
    private String responseCode;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    //@JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime responseDate;
    private String responseMessage;
}
