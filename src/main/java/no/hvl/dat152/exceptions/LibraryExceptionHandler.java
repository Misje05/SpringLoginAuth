/**
 * 
 */
package no.hvl.dat152.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 */
@ControllerAdvice
public class LibraryExceptionHandler {
	
	/**
	 * Specific error message
	 * @param model
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = BookNotFoundException.class)
	public String bookNotFound(Model model, BookNotFoundException ex) {
		
		model.addAttribute("error", "BookNotFoundException");
		model.addAttribute("status", Response.SC_NOT_FOUND);
		model.addAttribute("message", ex.getMessage());
		
		return "error";
	}
	
	/**
	 * Specific error message
	 * @param model
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = UpdateBookFailedException.class)
	public String updateBookFailed(Model model, UpdateBookFailedException ex) {
		
		model.addAttribute("error", "UpdateBookFailedException");
		model.addAttribute("status", Response.SC_NOT_FOUND);
		model.addAttribute("message", ex.getMessage());
		
		return "error";
	}
	
	/**
	 * All other types of error
	 * @param model
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public String otherErrors(Model model, Exception ex) {
		
		model.addAttribute("error", "Exception");
		model.addAttribute("status", Response.SC_INTERNAL_SERVER_ERROR);
		model.addAttribute("message", ex.getMessage());
		
		return "error";
	}
	
}
