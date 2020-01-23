package al.bruno.sholla.sftp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ApdApplication {

	
	@Autowired
	private callWebServices callws;
	
	public static void main(String[] args) {
		SpringApplication.run(ApdApplication.class, args);
	}
	
	
	@Scheduled(fixedRateString ="${leje.ditore.time.ms}", initialDelay=1000)
     public void work() throws IOException {
		 callws.callLejeDitore();
     }

}
