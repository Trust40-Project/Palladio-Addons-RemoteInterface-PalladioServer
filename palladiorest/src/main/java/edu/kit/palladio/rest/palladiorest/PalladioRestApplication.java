package edu.kit.palladio.rest.palladiorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import edu.kit.palladio.rest.client.RmiClientConfig;

@SpringBootApplication
@Import(RmiClientConfig.class)
//@ComponentScan({"edu.kit.palladio.rmi.filemanagment", "edu.kit.palladio.rest.palladiorest"})
public class PalladioRestApplication {


	
	

	public static void main(String[] args) {
		SpringApplication.run(PalladioRestApplication.class, args);
	}

}
