package com.ensaj.linstener;




import com.ensaj.configs.MQConfig;
import com.ensaj.model.Voiture;
import com.ensaj.repo.VoitureRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageListener {

    @Autowired
    private VoitureRepository voitureRepository; // Assuming you have a VoitureRepository
    private List<Voiture> lastReceivedVoitures = new ArrayList<>();

    @RabbitListener(queues = MQConfig.QUEUEREQUEST)
    public void processClientId(Long client) {
        System.out.println("Received Id Client From Client Producer: " + client);

        // Perform findById operation using the received clientId
        List<Voiture> voitures = voitureRepository.findByClient(client);
        lastReceivedVoitures.clear();

        lastReceivedVoitures.addAll(voitures);

        // Process the list of Voiture objects as needed
        System.out.println("Found Voitures: " + voitures);
    }
    public List<Voiture> getLastReceivedVoitures() {
        return lastReceivedVoitures;
    }

}
