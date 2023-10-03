package me.ruramaibotso.umc;

import me.ruramaibotso.umc.user.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class UmcApplication {
	static UserRepository userRepository;
	static  PasswordEncoder passwordEncoder;


	public UmcApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(UmcApplication.class, args);

		User user = new User();
		user.setFirstname("Ruramai");
		user.setLastname("Botso");
		user.setOrganisation(Organisation.UMYF);
		user.setRole(Role.Super_Admin);
		user.setPassword("sag");
		user.setDateOfBirth(LocalDate.of(2001, Month.of(8), 10));
		user.setGender(Gender.Male);
		user.setMembershipStatus(Membership.Full);
		user.setId(0);
		user.setMembershipNumber("Admin");
		user.setPassword(passwordEncoder.encode("admin@umc"));
		userRepository.save(user);

	}

}
