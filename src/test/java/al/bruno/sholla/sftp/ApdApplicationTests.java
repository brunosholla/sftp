package al.bruno.sholla.sftp;

import org.junit.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import al.bruno.sholla.sftp.services.MainService;

public class ApdApplicationTests {
	MainService ap= new MainService();

	
	private String remoteHost = "74.208.215.175";
	private String username = "u57006109-case";
	private String password = "Turi2020?!";

	private ChannelSftp setupJsch() throws JSchException {
		JSch jsch = new JSch();
		jsch.setKnownHosts("C:\\Users\\User\\.ssh\\known_hosts");
		Session jschSession = jsch.getSession(username, remoteHost);
		jschSession.setPassword(password);
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}

	@Test
	public void whenUploadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
		
	    ChannelSftp channelSftp = setupJsch();
	    channelSftp.connect();
	  
	    String localFile = "src/main/resources/sample.txt";
	   
	    String remoteDir = "/";
	  
	    channelSftp.put(localFile, remoteDir + "jschFile.txt");
	  
	   channelSftp.exit();
	}

}
