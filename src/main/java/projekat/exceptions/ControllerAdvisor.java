package projekat.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	
	//mogao sam ove 3 klase exceptiona staviti da nasljedjuju jedan HttpNotFoundException ali ok
	@ExceptionHandler(IngredientNotFoundException.class)
	public ResponseEntity<Object> handleIngredientNotFoundException(
			IngredientNotFoundException ex, WebRequest webRequest){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PancakeNotFoundException.class)
	public ResponseEntity<Object> handlePancakeNotFoundException(
			PancakeNotFoundException ex, WebRequest webRequest){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Object> handleOrderNotFoundException(
			OrderNotFoundException ex, WebRequest webRequest){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadPancakeException.class)
	public ResponseEntity<Object> handleBadPancakeException(
			BadPancakeException ex, WebRequest webRequest){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadOrderException.class)
	public ResponseEntity<Object> handleBadOrderException(
			BadOrderException ex, WebRequest webRequest){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadIngredientCategory.class)
	public ResponseEntity<Object> handleBadIngredientException(
			BadIngredientCategory ex, WebRequest webRequest){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	  public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Internal server error");
	    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

}
