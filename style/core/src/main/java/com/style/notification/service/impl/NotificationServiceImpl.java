package com.style.notification.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.style.notification.service.NotificationService;
import com.style.service.MailEngine;
import com.style.service.SMSEngine;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Message;

/**
 * Implementation of notification interface.
 * 
 * @author <a href="mailto:mathivanan18@gmail.com">Mathi</a>
 */

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    protected final Log log = LogFactory
            .getLog(com.style.notification.service.impl.NotificationServiceImpl.class);

    private SMSEngine smsEngine;

    private MailEngine mailEngine;

    @Autowired(required = false)
    public void setMailEngine(MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    @Autowired(required = false)
    public void setSmsEngine(final SMSEngine smsEngine) {
        this.smsEngine = smsEngine;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> sendEmail(Map<String, Object> requestMap) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            mailEngine.sendMessage(requestMap.get("recipients").toString()
                    .split(","), null, requestMap.get("subject").toString(),
                    requestMap.get("bodyText").toString());
            response.put("status", "success");
            response.put("message", "Mail sent.");
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            response.put("status", "error");
            response.put("message", "Problem in sending mail.");
        } catch (NullPointerException ne) {
            log.error(ne.getMessage(), ne);
            response.put("status", "error");
            response.put("message",
                    "recipients, subject, bodyText should not be empty");
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> sendSMS(Map<String, Object> requestMap) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            smsEngine.sendTwilioSms(requestMap.get("mobileNo").toString(),
                    requestMap.get("message").toString());
            response.put("status", "success");
            response.put("message", "Message sent.");
        } catch (TwilioRestException e) {
            log.error(e.getMessage(), e);
            response.put("status", "error");
            response.put("message", "Problem in sending message.");
        } catch (NullPointerException ne) {
            log.error(ne.getMessage(), ne);
            response.put("status", "error");
            response.put("message", "mobileNo, message should not be empty");
        }
        return response;
    }

}
