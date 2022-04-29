package org.mp.usermanagementapp.listener;

import org.mp.usermanagementapp.entity.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UsermanagementAppKafkaListener {

	@KafkaListener(topics = "netsurfingzone-topic-1", groupId = "ankur")
	public void listenForMessage(User user) {
		System.out.println("------------------");
		System.out.println("User: "+user);
	}
}
