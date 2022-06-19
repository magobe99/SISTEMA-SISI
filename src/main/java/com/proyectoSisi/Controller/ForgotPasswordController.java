package com.proyectoSisi.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectoSisi.Utility;
import com.proyectoSisi.model.Usuario;
import com.proyectoSisi.servicio.ResetPasswordService;
import com.proyectoSisi.servicio.UsuarioNotFoundException;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private ResetPasswordService resetPasswordService;
	
	@GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	return "contraseña";
        }
 
        return "redirect:/index";
		
 
    }
 
    
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);
         
        try {
        	resetPasswordService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Hemos enviado un enlace para restablecer la contraseña a su correo electrónico. Por favor, compruebe..");
             
        } catch (UsuarioNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error al enviar correo electrónico");
        }
             
        return "contraseña";
    }
    
    
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("sistemasisi123@gmail.com", "Soporte - SISI");
        helper.setTo(recipientEmail);
         
        String subject = "Aquí está el enlace para restablecer su contraseña";
         
        String content = "<p>Hola,</p>"
                + "<p>Ha solicitado restablecer su contraseña.</p>"
                + "<p>Haga clic en el enlace de abajo para cambiar su contraseña:</p>"
                + "<p><a href=\"" + link + "\">Cambiar mi contraseña</a></p>"
                + "<br>"
                + "<p>Ignora este correo electrónico si recuerdas tu contraseña, "
                + "o no has hecho la solicitud.</p>";
         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }
     
     
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Usuario usuaurio = resetPasswordService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
         
        if (usuaurio == null) {
            model.addAttribute("message", "Token invalido");
            return "login";
        }
         
        return "reset_password_form";
    }
     
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        Usuario usuaurio = resetPasswordService.getByResetPasswordToken(token);
        model.addAttribute("title", "Restablecer su contraseña");
         
        if (usuaurio == null) {
            model.addAttribute("message", "Token invalido");
            return "login";
        } else {           
        	resetPasswordService.updatePassword(usuaurio, password);
             
            model.addAttribute("message", "Has cambiado satisfactoriamente tu contraseña.");
        }
         
        return "login";
    }

}
