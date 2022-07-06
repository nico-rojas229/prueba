package webRTC.VideoCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import webRTC.VideoCall.entity.UserEntity;
import webRTC.VideoCall.repository.UserRepository;

@SpringBootApplication
public class VideoCallApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoCallApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(UserRepository repository) {
		return args -> {
			UserEntity item = new UserEntity(
					"1",
					1,
					"benito martinez ocasio",
					"benito",
					"hello12345"
			);

			System.out.println(repository.findByIdUser(1));
		};
	}*/

}
