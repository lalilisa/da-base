package com.movie.base.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libs.common.kafka.KafkaRequestHandler;
import com.libs.common.model.Message;
import com.movie.base.config.AppConf;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class KafkaConsumerHandler extends KafkaRequestHandler {

    @Autowired
    public KafkaConsumerHandler(AppConf appConf, ObjectMapper objectMapper){
        super(objectMapper, appConf.getKafkaHost(), appConf.getServiceName(),10);
    }
    @Override
    protected Object handle(Message message) throws Exception {
        return null;
    }
}
