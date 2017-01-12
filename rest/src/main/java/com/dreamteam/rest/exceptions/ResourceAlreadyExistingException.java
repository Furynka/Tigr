
package com.dreamteam.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jiri Oliva
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="Given resource is already exisitng")
public class ResourceAlreadyExistingException extends RuntimeException {
    
} 