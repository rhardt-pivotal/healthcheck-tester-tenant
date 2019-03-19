package io.pivotal.fe.rhardt.healthchecktesttenant;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@SpringBootApplication
@Controller
@Log
public class HealthCheckTestTenantApplication {

	@Autowired
	private AppStatusRepository appStatusRepository;

	public static void main(String[] args) {
		SpringApplication.run(HealthCheckTestTenantApplication.class, args);
	}

	@RequestMapping("/")
	public ResponseEntity<ApplicationInstanceInfo> index() {
		return ResponseEntity.ok(new CloudFactory().getCloud().getApplicationInstanceInfo());
	}


}
