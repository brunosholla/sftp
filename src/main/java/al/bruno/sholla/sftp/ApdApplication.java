package al.bruno.sholla.sftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import al.bruno.sholla.sftp.services.ApplicationProperties;

@EnableScheduling
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication(scanBasePackages = { "al.bruno.sholla.sftp" })
public class ApdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApdApplication.class, args);
	}

	/*
	 * @Scheduled(fixedRateString ="${execution.time.ms}", initialDelay=1000) public
	 * void work() throws IOException {
	 * 
	 * }
	 */
}
