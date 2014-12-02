package pe.mil.ejercito.sipr.commons;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final int CONFIGURACION_TRANSC;

    static {
        CONFIGURACION_TRANSC = 1;
    }


    public static int enviarCorreo( List<String[]> tags, String to, String subject, String mensaje,String pathFile,String nameFile) {
        if (to != null && !to.isEmpty()) {
        	
        	if(tags!=null && !tags.isEmpty()){
        		for(String[] tag : tags){
        			subject = subject.replace(tag[0], tag[1]==null?"":tag[1]);
        			mensaje = mensaje.replace(tag[0], tag[1]==null?"":tag[1]);
        		}
        	}
        	
            Properties props = new Properties();
            try {
                    props.put("mail.smtp.host",ConfiguracionDefault.HOST );
                    props.setProperty("mail.smtp.starttls.enable",ConfiguracionDefault.STARTTLS_ENABLE);
                    props.setProperty("mail.smtp.port", ConfiguracionDefault.PORT);
                    props.setProperty("mail.smtp.user",ConfiguracionDefault.USER );
                    props.setProperty("mail.smtp.auth",ConfiguracionDefault.AUTH);

                Session session = Session.getDefaultInstance(props, null);

                BodyPart texto = new MimeBodyPart();
                MimeBodyPart textoMime = new MimeBodyPart();
                String charset = ConfiguracionDefault.CHARSET;
                String contentType = ConfiguracionDefault.CONTENT_TYPE;
                textoMime.setText(mensaje, charset, contentType);
                texto = (BodyPart) textoMime;
                
                BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(new DataHandler(new FileDataSource(pathFile)));
                adjunto.setFileName(nameFile);

                MimeMultipart multiParte = new MimeMultipart();
                multiParte.addBodyPart(texto);
                multiParte.addBodyPart(adjunto);
                
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(ConfiguracionDefault.INTERNET_ADDRESS));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                if(ConfiguracionDefault.BCC!=null && ConfiguracionDefault.BCC.length()>0){
                	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(ConfiguracionDefault.BCC));	
                }
                
                message.setSubject(subject);
                message.setContent(multiParte);
                // Se envia el correo.
                Transport t = session.getTransport(ConfiguracionDefault.TRANSPORT);
                 t.connect(ConfiguracionDefault.EMAIL_CONNECT,ConfiguracionDefault.EMAIL_PASSWORD);
                t.sendMessage(message, message.getAllRecipients());
                t.close();

            } catch (MessagingException ex) {
                Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        } else {
            return -2;
        }
        return 1;
    }
    
}
