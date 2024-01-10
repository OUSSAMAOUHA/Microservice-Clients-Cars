package com.ensaj.linstener;


import com.ensaj.configs.MQConfig;
import com.ensaj.model.Voiture;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    public void processVoitures(List<Voiture> voitures) {
        System.out.println("Received Voitures From elhadi Voiture Producer: " + voitures);
    }
}
