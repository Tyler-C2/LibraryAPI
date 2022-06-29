package com.library.app.rest.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ApiErrorController implements ErrorController{

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return String.format("<html><body><h2>Error Page</h2><div><h3>Something went wrong.</h3></div>"
        		+ "<div>Status code: <b>%s</b></div>",
                statusCode);
    }

    public String getErrorPath() {
        return "/error";
    }
}

