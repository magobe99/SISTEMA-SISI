package com.proyectoSisi;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class CustomErrorController  implements ErrorController {
 
    @GetMapping("/error")
    public String handleError(HttpServletRequest request,Model model) {
        String errorPage = "error"; // default
         
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
             
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // handle HTTP 404 Not Found error
            	model.addAttribute("error", "404");
            	model.addAttribute("tittle", "¡UH OH! Estás perdido.");
            	model.addAttribute("message", "La página que está buscando no existe. Cómo llegaste aquí? es un misterio. Pero puede hacer clic en el botón de abajo para volver a la página de inicio.");
                errorPage = "error/error";
                 
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
            	
            	model.addAttribute("error", "403");
            	model.addAttribute("tittle", "Página PROHIBIDA !");
            	model.addAttribute("message", "Lo sentimos, no tienes permisos para ver esta pagina.");
                errorPage = "error/error";
                 
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // handle HTTP 500 Internal Server error
            	model.addAttribute("error", "500");
            	model.addAttribute("tittle", "Error de servidor interno !");
            	model.addAttribute("message", "El servidor encontró un ERROR INTERNO o una configuración incorrecta y no puede completar su solicitud.");
                errorPage = "error/error";
                 
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                // handle HTTP 401 Unavailable
            	model.addAttribute("error", "401");
            	model.addAttribute("tittle", "Autorización requerida !");
            	model.addAttribute("message", "Usted no tiene permiso para acceder a esta página.");
                errorPage = "error/error";
                 
            } else if (statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                // handle HTTP 503 Service Unavailable
            	model.addAttribute("error", "503");
            	model.addAttribute("tittle", "Servicio no disponible !");
            	model.addAttribute("message", "El servidor está temporalmente ocupado, intente nuevamente más tarde.");
                errorPage = "error/error";
                 
            }
        }
         
        return errorPage;
    }
     

    public String getErrorPath() {
        return "/error";
    }
}
