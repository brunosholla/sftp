package al.bruno.sholla.sftp.controllers;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.stereotype.Service;

import al.bruno.sholla.sftp.services.ApplicationProperties;
import al.bruno.sholla.sftp.services.MainServices;

@Service("sftpIcon")
public class sftpIcon {

	Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/images/ftp.png"));
	Image image_error = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/images/ftp_error.png"));

	private Image img;

	private MainServices mainServices;
	private ApplicationProperties applicationProperties;

	public sftpIcon(MainServices mainServices, ApplicationProperties applicationProperties) {
		this.mainServices = mainServices;
		this.applicationProperties = applicationProperties;
	}

	public void createAndShowTray() throws Exception {

		// Check the SystemTray is supported
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}

		if (!mainServices.SFTPaddedToKnownFolder()) {
			img = image_error;
		} else {
			img = image;
		}
		PopupMenu popup = new PopupMenu();
		// retrieve icon form url and scale it to 32 x 32
		final TrayIcon trayIcon = new TrayIcon(img.getScaledInstance(16, 16, Image.SCALE_DEFAULT), null);
		trayIcon.setToolTip("SFTP Connection");
		// get the system tray
		final SystemTray tray = SystemTray.getSystemTray();

		// Create a pop-up menu components
		MenuItem aboutItem = new MenuItem("About");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				getProperties();
			}

		});

		MenuItem Synch = new MenuItem("Upload csv");
		Synch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				mainServices.uploadFile();
			}

		});

		/*
		 * final CheckboxMenuItem cb1 = new CheckboxMenuItem("Show Tooltip");
		 * cb1.addItemListener(new ItemListener() {
		 * 
		 * @Override public void itemStateChanged(ItemEvent ie) { if (cb1.getState() ==
		 * true) { trayIcon.setToolTip("Hello, world"); } else {
		 * trayIcon.setToolTip(""); } } });
		 */

		MenuItem exitItem = new MenuItem("Exit");
		// add actionlistner to Exit menu item
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		// Add components to pop-up menu
		popup.add(aboutItem);
		popup.addSeparator();
		popup.add(Synch);
		popup.addSeparator();
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}
	}

	private void getProperties() {
		JTextField Hostname = new JTextField(applicationProperties.getSFTPHostname());
		JTextField Username = new JTextField(applicationProperties.getSFTPUsername());
		JTextField localCSVPath = new JTextField(applicationProperties.getLocalCSVPath());
		JTextField localLOGPath = new JTextField(applicationProperties.getLocalLogPath());
		JTextField executionTIMEms = new JTextField(mainServices.getDurationBreakdown(applicationProperties.getExecutionTimeMS()));

		Object[] message = { "Hostname:", Hostname, "Username:", Username, "CSV Path:", localCSVPath, "LOG Path:",
				localLOGPath,"Scheduling time (ms)",executionTIMEms

		};

		JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}