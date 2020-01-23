package al.bruno.sholla.sftp.services;

import org.springframework.beans.factory.annotation.Value;

public class MainService {
	
	@Value("${execution.time.ms}")
	private Long executionTimeMS;
	
	@Value("${remote.csv.path}")
	private String remoteCSVPath;
	
	@Value("${remote.csv.name}")
	private String remoteCSVName;
	
	@Value("${SFTP.Hostname}")
	private String SFTPHostname;
	
	@Value("${SFTP.username}")
	private String SFTPUsername;
	
	@Value("${SFTP.password}")
	private String SFTPPassword;
	

}
