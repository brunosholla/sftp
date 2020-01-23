package al.bruno.sholla.sftp.services;

import org.springframework.beans.factory.annotation.Value;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


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

	@Value("${local.log.path}")
	private String localLogPath;

	@Value("${local.log.name}")
	private String localLogName;

	public ChannelSftp setupJsch() throws JSchException {
		
		JSch jsch = new JSch();
		jsch.setKnownHosts("C:\\Users\\User\\.ssh\\known_hosts");
		Session jschSession = jsch.getSession(SFTPUsername, SFTPHostname);
		jschSession.setPassword(SFTPPassword);
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}
}
