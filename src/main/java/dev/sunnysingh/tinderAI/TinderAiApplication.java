package dev.sunnysingh.tinderAI;

import dev.sunnysingh.tinderAI.conversations.ChatMessage;
import dev.sunnysingh.tinderAI.conversations.Conversation;
import dev.sunnysingh.tinderAI.conversations.ConversationRepository;
import dev.sunnysingh.tinderAI.profile.Gender;
import dev.sunnysingh.tinderAI.profile.Profile;
import dev.sunnysingh.tinderAI.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		profileRepository.deleteAll();
		conversationRepository.deleteAll();
		System.out.println("My application is Running");
		Profile profile=new Profile("1",
				"John","Skully", 40,
				"Indian", Gender.Male,"Softwaredev",
				"foo.jpg", "INFJ");
		Profile profile2=new Profile("2",
				"Foo","Baar", 40,
				"Indian", Gender.Male,"Softwaredev",
				"foo.jpg", "INFJ");
		profileRepository.save(profile);
		profileRepository.findAll()
				.forEach((p)->{
					System.out.println(p);
				});
		Conversation conversation=new Conversation(
				"23",
				profile.id(),
				List.of(
						new ChatMessage(
								"Hello",
									profile.id(),
								LocalDateTime.now()

						)
				)


		);
		conversationRepository.save(conversation);
		conversationRepository.findAll()
				.forEach((p)->{
					System.out.println(p);
				});

	}
}
