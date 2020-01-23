package al.bruno.sholla.sftp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class MainServices {

	@Autowired
	public ApplicationProperties ap;
	
	@Autowired
	public cmd cmd;

	private ChannelSftp setupJsch() throws JSchException {

		JSch jsch = new JSch();
		jsch.setKnownHosts("C:\\Users\\User\\.ssh\\known_hosts");
		Session jschSession = jsch.getSession(ap.getSFTPUsername(), ap.getSFTPHostname());
		jschSession.setPassword(ap.getSFTPPassword());
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}

	public void uploadFile() {

		ChannelSftp channelSftp;
		try {

			channelSftp = setupJsch();
			channelSftp.connect();

			try {
				channelSftp.put(ap.getLocalCSVPath(),ap.getRemoteCSVPath());
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			channelSftp.exit();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}