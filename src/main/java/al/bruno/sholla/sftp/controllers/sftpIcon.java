package al.bruno.sholla.sftp.controllers;

import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import al.bruno.sholla.sftp.services.MainServices;

@Service
@Configurable
public class sftpIcon {

	@Autowired
	private MainServices mainServices;

	Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/images/ftp.png"));
	Image image_error = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/images/ftp_error.png"));
	private Image img;


	public void createTrayIcon() throws Exception {
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}

		System.err.println(mainServices);
		
		if(!mainServices.SFTPaddedToKnownFolder()) {
			img=image_error;
		}else {
			img=image;
		}
		SystemTray tray = SystemTray.getSystemTray();

		PopupMenu menu = createMenu();
		TrayIcon icon = new TrayIcon(img, "SFTP connector", menu);
		icon.setImageAutoSize(true);

		tray.add(icon);
	}

	private PopupMenu createMenu() {
		PopupMenu menu = new PopupMenu();

		menu.setName("SFTP Software");
		menu.addSeparator();

		Menu InfoMenu = new Menu("Information");
		MenuItem infoItem = new MenuItem("CR Info");
		infoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "www.java2s.com");
			}
		});

		MenuItem infoSubItem = new MenuItem("Cash Balance");
		infoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "www.java2s.com");
			}
		});
		InfoMenu.add(infoItem);
		InfoMenu.add(infoSubItem);

		menu.add(InfoMenu);

		MenuItem helpItem = new MenuItem("Help");
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
			}
		});
		menu.add(helpItem);

		MenuItem closeItem = new MenuItem("Close");
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(closeItem);
		return menu;
	}
}