package al.bruno.sholla.sftp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import al.bruno.sholla.sftp.controllers.sftpIcon;
import al.bruno.sholla.sftp.services.ApplicationProperties;
import al.bruno.sholla.sftp.services.MainServices;

@EnableScheduling
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication(scanBasePackages = { "al.bruno.sholla.sftp" })
public class SFTPApplication {

	@Autowired
	private static sftpIcon sftpIcon;

	@Autowired
	private static MainServices mainServices;

	public SFTPApplication(MainServices mainServices, sftpIcon sftpIcon) {
		SFTPApplication.mainServices = mainServices;
		SFTPApplication.sftpIcon = sftpIcon;
	}

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) throws Exception {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(SFTPApplication.class);
		builder.headless(false);
		context = builder.run(args);

		boolean alreadyExecuted = false;
		if (!alreadyExecuted) {

			sftpIcon.createAndShowTray();

			alreadyExecuted = true;
		}

	}

	@Scheduled(fixedRateString = "${execution.time.ms}", initialDelay = 1000)
	public void work() throws IOException {
		mainServices.uploadFile();
	}

}
