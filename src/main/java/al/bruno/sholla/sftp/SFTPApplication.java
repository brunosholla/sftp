package al.bruno.sholla.sftp;

import java.io.IOException;

import javax.annotation.PostConstruct;

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

		if (!mainServices.SFTPaddedToKnownFolder()) {
			try {
				mainServices.addHostToKnown();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Scheduled(fixedRateString = "${execution.time.ms}", initialDelay = 5000)
	public void work() throws Exception {

		Thread.sleep(5000);
		sftpIcon.createAndShowTray();
		mainServices.uploadFile();

	}

}
