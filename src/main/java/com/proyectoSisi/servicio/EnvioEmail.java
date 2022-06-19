package com.proyectoSisi.servicio;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class EnvioEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String subject, String content, String email, String fileName ,MultipartFile multipartFile) {

        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
        	
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            

            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true);
            
            ClassPathResource resource = new ClassPathResource("/static/img/SISI_footer.png");
    		helper.addInline("logoImage", resource);
    		
    		if (!multipartFile.isEmpty()) {
    		
    			
    			InputStreamSource source = new InputStreamSource() {
    				
    				@Override
    				public InputStream getInputStream() throws IOException {
    					return multipartFile.getInputStream();
    				}
    			};
    			
    			helper.addAttachment(fileName, source);
    		}
    		
 

            javaMailSender.send(msg);
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
