package al.bruno.sholla.sftp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class MainServices {

	@Autowired
	public ApplicationProperties ap;

	public ChannelSftp setupJsch() throws JSchException {
		
		System.err.println(ap.getRemoteCSVName());
		JSch jsch = new JSch();
		jsch.setKnownHosts("C:\\Users\\User\\.ssh\\known_hosts");
		Session jschSession = jsch.getSession(ap.getSFTPUsername(), ap.getSFTPHostname());
		jschSession.setPassword(ap.getSFTPPassword());
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}
}