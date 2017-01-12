
package com.dreamteam.rest.mixins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Jiri Oliva
 */
@JsonIgnoreProperties({ "passwordHash"})
public class WorkerDTOMixin {
    
}
