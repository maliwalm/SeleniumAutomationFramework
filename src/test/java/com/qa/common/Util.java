package com.qa.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;



public class Util {
	private static Logger log = Logger.getLogger(Util.class);

	private Util() {
		PropertyConfigurator.configure("log4j.properties");
		log.info(" : Util Constructor Called");
	}

	public static String generateUniqueName() {
		log.info(" : GenerateUniqueNameMethod Method Called");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_HHmmss_z");
		Date now = new Date();
		String uniqueText = sdfDate.format(now);
		return (uniqueText);
	}


	public static String captureScreenshot(EventFiringWebDriver eDriver, String screenshotName) {
		log.info(" : CaptureScreeshot Method Called");
		try {
			TakesScreenshot ts = (TakesScreenshot) eDriver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = Config.ScreenShotsPath + screenshotName + "_" + Util.generateUniqueName() + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			return dest;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
			return ex.getMessage();
		}
	}


	public static void setScreenshotRelativePath() {
		FileReader fr = null;
		FileWriter fw = null;
		log.info(" : sshotSetRelativePath Method Called");
		try {
			File f = new File(Config.ExtentReportsPath);
			// File f = new File(Config.ScreenShotsPath + "extentreport.html");
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("<img")) {
					line = line.replace(Config.ScreenShotsPath, "./");
				}
				lines.add(line + "\n");
			}
			fr.close();
			br.close();
			fw = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fw);
			for (String s : lines)
				out.write(s);
			out.flush();
			fw.close();
			out.close();
		} catch (FileNotFoundException ex) {
			log.error(ex.getMessage());
			System.err.println("INFO : extentreport.html doesnt exist at the moment.");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void zipFolder(final Path screenShotsPath, Path zipPath) throws Exception {
		log.info(" : zipfolder Method Called");
		final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
		Files.walkFileTree(screenShotsPath, new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				zos.putNextEntry(new ZipEntry(screenShotsPath.relativize(file).toString()));
				Files.copy(file, zos);
				zos.closeEntry();
				return FileVisitResult.CONTINUE;
			}
		});
		zos.close();
	}

	public static void SendMail(String filePath) throws MessagingException {
		log.info(" : SendMail Method Called");
		String to = Config.mailTo;
		String from = Config.mailFrom;
		final String username = Config.mailFrom;
		final String password = Config.mailPassword;
		String host = Config.mailHost;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("NewTours Demo Functional Automation test Report");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hi All , Please find the attached Automation Test execution Report.");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String[] filename = filePath.split("\\\\");
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename[filename.length - 1]);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent Test report on Email successfully....");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


	public static void isFolderExistAtPath(String filePath) {
		log.info(" : isFolderExistAtPath Method Called");
		File file = new File(filePath);
		if (file.exists() && file.isDirectory()) {
			System.out.println("Reports Folder exists at the path " + Config.ScreenShotsPath);
		} else {
			System.out.println("Reports Folder doesn't exist at the path " + Config.ScreenShotsPath);
			System.out.println("Creating Folder");
			file.mkdir();
		}

	}
}
