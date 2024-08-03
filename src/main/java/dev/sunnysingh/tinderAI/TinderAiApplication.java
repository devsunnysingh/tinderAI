package dev.sunnysingh.tinderAI;

import dev.sunnysingh.tinderAI.profile.Gender;
import dev.sunnysingh.tinderAI.profile.Profile;
import dev.sunnysingh.tinderAI.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {
	@Autowired
	ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("My application is Running");
		Profile profile=new Profile("1",
				"John","Skully", 40,
				"Indian", Gender.Male,"Softwaredev",
				"foo.jpg", "INFJ");
		profileRepository.save(profile);
		profileRepository.findAll()
				.forEach((p)->{
					System.out.println(p);
				});

	}
}
