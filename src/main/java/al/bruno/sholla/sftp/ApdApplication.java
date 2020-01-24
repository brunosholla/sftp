package al.bruno.sholla.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import al.bruno.sholla.sftp.controllers.sftpIcon;
import al.bruno.sholla.sftp.services.ApplicationProperties;


@EnableScheduling
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication(scanBasePackages = { "al.bruno.sholla.sftp" })
public class ApdApplication {


	@Autowired
	sftpIcon sftpIcon;
	
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) throws Exception {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ApdApplication.class);
		builder.headless(false);
		context = builder.run(args);

		boolean alreadyExecuted = false;
		if (!alreadyExecuted) {
			 sftpIcon sftpIcon = new sftpIcon();
			 sftpIcon.createTrayIcon();
			alreadyExecuted = true;
		}

	}

	/*
	 * @Scheduled(fixedRateString ="${execution.time.ms}", initialDelay=1000) public
	 * void work() throws IOException {
	 * 
	 * }
	 */
}
