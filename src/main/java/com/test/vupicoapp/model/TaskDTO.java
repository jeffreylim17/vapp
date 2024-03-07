package com.test.vupicoapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema
public class TaskDTO {

    private long taskId;
    private Status status;
    private String details;
}
