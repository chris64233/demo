package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MoveConfig {

    @Value("${move.gateway.switched}")
    private boolean switched;

    @Value("${move.history.mcActivity.maxUpdateTime}")
    private String mcActivityMaxUpdateTime;

    @Value("${move.history.mcActivityModuleDesc.maxUpdateTime}")
    private String mcActivityModuleDescMaxUpdateTime;

    @Value("${move.history.mcActivityUser.maxUpdateTime}")
    private String mcActivityUserMaxUpdateTime;

    @Value("${move.history.mcQuestionAnswer.maxUpdateTime}")
    private String mcQuestionAnswerMaxUpdateTime;

    @Value("${move.history.mcOperationRecord.maxUpdateTime}")
    private String mcOperationRecordMaxUpdateTime;

    @Value("${move.stage}")
    private Integer stage;

}
