package helpers;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

public class GetRequestParamsHelper {

    /**
     * Get params from request
     * @param request
     * @return
     */
    public static HashMap<String, String> getRequestParams(HttpServletRequest request){
        HashMap<String, String> requestParams = new HashMap<>();
        Enumeration en = request.getParameterNames();
        while(en.hasMoreElements()){
            Object obj = en.nextElement();
            String paramName = (String)obj;
            String paramValue = request.getParameter(paramName);
            requestParams.put(paramName, paramValue);
        }
        return requestParams;
    }
}
