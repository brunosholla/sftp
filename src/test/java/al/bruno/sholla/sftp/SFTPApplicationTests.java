package al.bruno.sholla.sftp;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import al.bruno.sholla.sftp.services.ApplicationProperties;
import al.bruno.sholla.sftp.services.MainServices;
import al.bruno.sholla.sftp.services.cmd;

@SpringBootTest
public class SFTPApplicationTests {

	@Autowired
	MainServices mainServices;

	@Autowired
	private ApplicationProperties app;

	@Test
	public void test() throws IOException {
		mainServices.uploadFile();

	}

	

}
