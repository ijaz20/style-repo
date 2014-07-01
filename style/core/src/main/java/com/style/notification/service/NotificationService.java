package com.style.notification.service;

import java.util.Map;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Web Service interface to send notifications.
 * 
 * @author <a href="mailto:mathivanan18@gmail.com">Mathi</a>
 */
@WebService
@Path("/notifications")
public interface NotificationService {

    /**
     * send E-Mail
     * 
     * @param requestMap
     * @return
     */
    @POST
    @Path("/sendEmail")
    @Produces("application/json")
    Map<String, Object> sendEmail(Map<String, Object> requestMap);

    /**
     * send SMS
     * 
     * @param requestMap
     * @return
     */
    @POST
    @Path("/sendSMS")
    @Produces("application/json")
    Map<String, Object> sendSMS(Map<String, Object> requestMap);
}
