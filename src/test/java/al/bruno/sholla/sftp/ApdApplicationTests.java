package al.bruno.sholla.sftp;

import org.junit.Test;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import al.bruno.sholla.sftp.services.MainServices;

public class ApdApplicationTests {
	
	

	@Test
	public void whenUploadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
		MainServices ms = new MainServices();
		ChannelSftp channelSftp = ms.setupJsch();
		channelSftp.connect();

		String localFile = "src/main/resources/sample.txt";

		String remoteDir = "/";

		channelSftp.put(localFile, remoteDir + "jschFile.txt");

		channelSftp.exit();
	}

}
