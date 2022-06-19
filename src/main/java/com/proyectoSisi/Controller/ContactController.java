package com.proyectoSisi.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.proyectoSisi.Repository.ClienteRepository;
import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.model.Cliente;
import com.proyectoSisi.model.Usuario;
import com.proyectoSisi.servicio.EnvioEmail;

@Controller
public class ContactController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EnvioEmail envioEmail;

	@GetMapping("/")
	public String showContactForm() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	return "Inicio";
        }
 
        return "redirect:/index";
		
	}
	
	@GetMapping("/succeful")
	public String showSucceful() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	return "message";
        }
 
        return "redirect:/index";
		
	}

	@PostMapping("/send")
	public String submitContact(HttpServletRequest request, @RequestParam("attachment") MultipartFile multipartFile)
			throws MessagingException, UnsupportedEncodingException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		/* SimpleMailMessage message = new SimpleMailMessage(); */
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		String mailSubject = fullname + " ha enviado un mensaje";
		String mailContent = "<p><b>Nombre del remitente: </b>" + fullname + "</p>";
		mailContent += "<p><b>Correo electrónico del remitente: </b>" + email + "</p>";
		mailContent += "<p><b>Asunto: </b>" + subject + "</p>";
		mailContent += "<p><b>Mensaje: </b>" + content + "</p>";
		mailContent += "<hr><img src='cid:logoImage' />";

		helper.setFrom("sistemasisi123@gmail.com", " SISI Contacto");
		helper.setTo("sistemasisi123@gmail.com");
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);

		ClassPathResource resource = new ClassPathResource("/static/img/SISI_footer.png");
		helper.addInline("logoImage", resource);

		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

			InputStreamSource source = new InputStreamSource() {

				@Override
				public InputStream getInputStream() throws IOException {
					return multipartFile.getInputStream();
				}
			};

			helper.addAttachment(fileName, source);
		}

		mailSender.send(message);
		
		return "redirect:/succeful";
		
	}

	//////////////////////////////// ENVIO DE CORREOS
	//////////////////////////////// MASIVOS CLIENTES/////////////////////////////////////////////////

	@GetMapping("/contact")
	public String showContactClientsForm() {

		return "contact_form";
	}

	@PostMapping("/contact")
	public String submitContactC(HttpServletRequest request, RedirectAttributes redirectAttrs,
			@RequestParam("attachment") MultipartFile multipartFile)
			throws MessagingException, UnsupportedEncodingException {

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		List<Cliente> listClients = clienteRepository.findAll();

		String mailContent = "<p><b>Nombre del remitente: </b>" + fullname + "</p>";
		mailContent += "<p><b>Correo de contacto: </b>" + email + "</p>";
		mailContent += "<p><b>Pagina Web: </b> https://co.titaniumstore.co </p>";
		mailContent += "<p><b>Asunto: </b>" + subject + "</p>";
		mailContent += "<p><b>Mensaje: </b>" + content + "</p>";
		mailContent += "<hr><img src='cid:logoImage' />";

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		try {

			for (Cliente c : listClients) {

				try {
					envioEmail.sendEmail(subject, mailContent, c.getCorreo(), fileName, multipartFile);

				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar los correos")
							.addFlashAttribute("clase", "danger");
					return "redirect:/contact";
				}

			}

			redirectAttrs.addFlashAttribute("mensaje", "Los correos se enviaron exitosamente")
					.addFlashAttribute("clase", "warning");
			return "redirect:/contact";

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar todos los mensajes")
					.addFlashAttribute("clase", "danger");
			return "redirect:/contact";
		}
	}

////////////////////////////////ENVIO DE CORREOS
//////////////////////////////// MASIVOS USUARIOS/////////////////////////////////////////////////

	@GetMapping("/contact2")
	public String showContactUsersForm() {

		return "contact_form2";
	}

	@PostMapping("/contact2")
	public String submitContactU(HttpServletRequest request, RedirectAttributes redirectAttrs,
			@RequestParam("attachment") MultipartFile multipartFile)
			throws MessagingException, UnsupportedEncodingException {

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		List<Usuario> listUsers = usuarioRepositorio.findAll();


		String mailContent = "<p><b>Nombre del remitente: </b>" + fullname + "</p>";
		mailContent += "<p><b>Correo de contacto: </b>" + email + "</p>";
		mailContent += "<p><b>Asunto: </b>" + subject + "</p>";
		mailContent += "<p><b>Mensaje: </b>" + content + "</p>";
		mailContent += "<hr><img src='cid:logoImage' />";

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		try {

			for (Usuario u : listUsers) {

				try {
					envioEmail.sendEmail(subject, mailContent, u.getEmail(), fileName, multipartFile);

				} catch (Exception e) {
					redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar los correos")
							.addFlashAttribute("clase", "danger");
					return "redirect:/contact2";
				}

			}

			redirectAttrs.addFlashAttribute("mensaje", "Los correos se enviaron exitosamente")
					.addFlashAttribute("clase", "warning");
			return "redirect:/contact2";

		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("mensaje", "No se pudo enviar todos los mensajes")
					.addFlashAttribute("clase", "danger");
			return "redirect:/contact2";
		}
	}
}
