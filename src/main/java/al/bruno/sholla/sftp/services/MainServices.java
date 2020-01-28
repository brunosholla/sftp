package al.bruno.sholla.sftp.services;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service("mainServices")
public class MainServices {

	@Autowired
	public ApplicationProperties ap;

	@Autowired
	public cmd cmd;

	 Logger logger = LoggerFactory.getLogger(MainServices.class);
	    
	
	private ChannelSftp setupJsch() throws JSchException {

		JSch jsch = new JSch();
		jsch.setKnownHosts(System.getProperty("user.home")+"\\.ssh\\known_hosts");
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
				
				channelSftp.put(ap.getLocalCSVPath(), ap.getRemoteCSVPath());
				logger.error("File u uploadua me sukses");
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				logger.error("Ndodhi nje error gjate upladimit");
				e.printStackTrace();
			}

			channelSftp.exit();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addHostToKnown() throws IOException {
		
		if (!SFTPaddedToKnownFolder()) {
			String addSFTPtoKnownHosts = "cd \"" + System.getProperty("user.home") + "\\.ssh\""
					+ " && ssh-keyscan -H -t rsa " + ap.getSFTPHostname() + " >> known_hosts";
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", addSFTPtoKnownHosts);

			builder.redirectErrorStream(true);
			builder.start();
		}
	}
	
	public boolean SFTPaddedToKnownFolder(){
		
		String comm = " ssh-keygen -F " + ap.getSFTPHostname();
		//System.err.println(comm);
		try {
			return Runtime.getRuntime().exec(comm).getInputStream().read() > 0;
		} catch (IOException e) {
			return true;
		}
		
	}
	
	 public String getDurationBreakdown(long millis) {
	        if(millis < 0) {
	            throw new IllegalArgumentException("Duration must be greater than zero!");
	        }

	        long days = TimeUnit.MILLISECONDS.toDays(millis);
	        millis -= TimeUnit.DAYS.toMillis(days);
	        long hours = TimeUnit.MILLISECONDS.toHours(millis);
	        millis -= TimeUnit.HOURS.toMillis(hours);
	        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
	        millis -= TimeUnit.MINUTES.toMillis(minutes);
	        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

	        StringBuilder sb = new StringBuilder(64);
	        sb.append(days);
	        sb.append(" Days ");
	        sb.append(hours);
	        sb.append(" Hours ");
	        sb.append(minutes);
	        sb.append(" Minutes ");
	        sb.append(seconds);
	        sb.append(" Seconds");

	        return(sb.toString());
	    }
}