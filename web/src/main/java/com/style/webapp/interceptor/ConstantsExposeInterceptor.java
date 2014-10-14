package com.style.webapp.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ijaz on 30/9/14.
 */
public class ConstantsExposeInterceptor implements Interceptor {

    private static final String PREFIX_FOR_ENUM_LISTS_IN_JSP = "enum_";
    private static final String PREFIX_FOR_OTHER_LISTS_IN_JSP = "list_";

    private Map<String, Map> linkedHashMap = new LinkedHashMap<String, Map>();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            for (String enumName : linkedHashMap.keySet()) {
                ServletActionContext.getContext().getValueStack().set(enumName, linkedHashMap.get(enumName));
            }
            return invocation.invoke();
        } catch (AccessDeniedException e) {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    private void setTimeInterval(){
        Map<String, String> valMap = new LinkedHashMap<String, String>() {{
            put("0", "12 AM - 01 AM");
            put("1", "01 AM - 02 AM");
            put("2", "02 AM - 03 AM");
            put("3", "03 AM - 04 AM");
            put("4", "04 AM - 05 AM");
            put("5", "05 AM - 06 AM");
            put("6", "06 AM - 07 AM");
            put("7", "07 AM - 08 AM");
            put("8", "08 AM - 09 AM");
            put("9", "09 AM - 10 AM");
            put("10", "10 AM - 11 AM");
            put("11", "11 AM - 12 PM");
            put("12", "12 PM - 01 PM");
            put("13", "01 PM - 02 PM");
            put("14", "02 PM - 03 PM");
            put("15", "03 PM - 04 PM");
            put("16", "04 PM - 05 PM");
            put("17", "05 PM - 06 PM");
            put("18", "06 PM - 07 PM");
            put("19", "07 PM - 08 PM");
            put("20", "08 PM - 09 PM");
            put("21", "09 PM - 10 PM");
            put("22", "10 PM - 11 PM");
            put("23", "11 PM - 12 AM");

        }};

        linkedHashMap.put(PREFIX_FOR_OTHER_LISTS_IN_JSP + "timeInterval", valMap);
    }
    /**
     * This method currently does nothing.
     */
    @Override
    public void destroy() {
    }

    /**
     * This method currently does nothing.
     */
    @Override
    public void init() {
        setTimeInterval();
    }
}