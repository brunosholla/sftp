package al.bruno.sholla.sftp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
public class ApplicationProperties {

	public ApplicationProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Value("${execution.time.ms}")
	private Long executionTimeMS;

	@Value("${remote.csv.path}")
	private String remoteCSVPath;

	@Value("${local.csv.path}")
	private String localCSVPath;

	@Value("${remote.csv.name}")
	private String remoteCSVName;

	@Value("${SFTP.Hostname}")
	private String SFTPHostname;

	@Value("${SFTP.username}")
	private String SFTPUsername;

	@Value("${SFTP.password}")
	private String SFTPPassword;

	@Value("${SFTP.channelType}")
	private String SFTPChannelType;

	@Value("${local.log.path}")
	private String localLogPath;

	public Long getExecutionTimeMS() {
		return executionTimeMS;
	}

	public void setExecutionTimeMS(Long executionTimeMS) {
		this.executionTimeMS = executionTimeMS;
	}

	public String getRemoteCSVPath() {
		return remoteCSVPath;
	}

	public void setRemoteCSVPath(String remoteCSVPath) {
		this.remoteCSVPath = remoteCSVPath;
	}

	public String getLocalCSVPath() {
		return localCSVPath;
	}

	public void setLocalCSVPath(String localCSVPath) {
		this.localCSVPath = localCSVPath;
	}

	public String getRemoteCSVName() {
		return remoteCSVName;
	}

	public void setRemoteCSVName(String remoteCSVName) {
		this.remoteCSVName = remoteCSVName;
	}

	public String getSFTPHostname() {
		return SFTPHostname;
	}

	public void setSFTPHostname(String sFTPHostname) {
		SFTPHostname = sFTPHostname;
	}

	public String getSFTPUsername() {
		return SFTPUsername;
	}

	public void setSFTPUsername(String sFTPUsername) {
		SFTPUsername = sFTPUsername;
	}

	public String getSFTPPassword() {
		return SFTPPassword;
	}

	public void setSFTPPassword(String sFTPPassword) {
		SFTPPassword = sFTPPassword;
	}

	public String getSFTPChannelType() {
		return SFTPChannelType;
	}

	public void setSFTPChannelType(String sFTPChannelType) {
		SFTPChannelType = sFTPChannelType;
	}

	public String getLocalLogPath() {
		return localLogPath;
	}

	public void setLocalLogPath(String localLogPath) {
		this.localLogPath = localLogPath;
	}

}
