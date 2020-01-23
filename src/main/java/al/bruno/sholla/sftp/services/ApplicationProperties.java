package al.bruno.sholla.sftp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

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

	@Value("${local.log.path}")
	private String localLogPath;

	@Value("${local.log.name}")
	private String localLogName;

	public Long getExecutionTimeMS() {
		return executionTimeMS;
	}

	public String getRemoteCSVPath() {
		return remoteCSVPath;
	}

	public String getRemoteCSVName() {
		return remoteCSVName;
	}

	public String getSFTPHostname() {
		return SFTPHostname;
	}

	public String getSFTPUsername() {
		return SFTPUsername;
	}

	public String getSFTPPassword() {
		return SFTPPassword;
	}

	public String getLocalLogPath() {
		return localLogPath;
	}

	public String getLocalLogName() {
		return localLogName;
	}

	public void setExecutionTimeMS(Long executionTimeMS) {
		this.executionTimeMS = executionTimeMS;
	}

	public void setRemoteCSVPath(String remoteCSVPath) {
		this.remoteCSVPath = remoteCSVPath;
	}

	public void setRemoteCSVName(String remoteCSVName) {
		this.remoteCSVName = remoteCSVName;
	}

	public void setSFTPHostname(String sFTPHostname) {
		SFTPHostname = sFTPHostname;
	}

	public void setSFTPUsername(String sFTPUsername) {
		SFTPUsername = sFTPUsername;
	}

	public void setSFTPPassword(String sFTPPassword) {
		SFTPPassword = sFTPPassword;
	}

	public void setLocalLogPath(String localLogPath) {
		this.localLogPath = localLogPath;
	}

	public void setLocalLogName(String localLogName) {
		this.localLogName = localLogName;
	}

}
