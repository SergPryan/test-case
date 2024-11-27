package com.example.demo.kafka;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenerExample {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "topic-1, topic-2", groupId = "group1")
    public void listener(@Payload String data) {
        try {
            Task task = objectMapper.readValue(data, Task.class);
            if (task.getId() != null && task.getDescription() != null) {
                taskRepository.save(task);
            }
        } catch (Exception e) {
           log.error("произошла ошибка {}", e.getMessage());
        }
    }
}
