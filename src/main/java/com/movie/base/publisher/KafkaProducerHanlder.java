package com.movie.base.publisher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.libs.common.exceptions.GeneralException;
import com.libs.common.kafka.KafkaRequestSender;
import com.libs.common.model.Message;
import com.libs.common.model.Response;
import com.movie.base.config.AppConf;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerHanlder extends KafkaRequestSender {
    private AppConf appConf;
    private ObjectMapper objectMapper;

    @Autowired
    public KafkaProducerHanlder(
            ObjectMapper objectMapper,
            AppConf appConf) {
        super(objectMapper, appConf.getKafkaHost(), appConf.getServiceName(), appConf.getInstanceId());
        this.setDefaultTimeout(18000);
        this.appConf = appConf;
        this.objectMapper = objectMapper;
    }

    public <T> T sendKafka(String topic, String uri, Object req, Class<T> clazz) {
        try {
            Message message = this.sendAsyncRequest(topic, uri, this.sourceId, req).get();
            if (message == null || message.getData() == null) {
                return null;
            }
            Response<T> response = Message.getData(objectMapper, message, new TypeReference<>() {
            });
            if (response.getStatus() != null) {
                throw new GeneralException(response.getStatus());
            }
            return objectMapper.convertValue(response.getData(), clazz);
        } catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralException().source(e);
        }
    }

    public <T> T sendKafka(String topic, String uri, Object req, TypeReference<T> typeReference) {
        try {
            Message message = this.sendAsyncRequest(topic, uri, this.sourceId, req).get();
            if (message == null || message.getData() == null) {
                return null;
            }
            Response<T> response = Message.getData(objectMapper, message, new TypeReference<>() {
            });
            if (response.getStatus() != null) {
                throw new GeneralException(response.getStatus());
            }
            return objectMapper.convertValue(response.getData(), typeReference);
        } catch (GeneralException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralException().source(e);
        }
    }

}
