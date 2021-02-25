package com.education.config.webScoket;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author admin
 * @Date 2020-12-16 19:04
 * @Version 1.0
 * @Description
 */

@Component
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre)  {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) sre.getServletRequest()).getSession();

    }
    public RequestListener() {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0)  {
    }
}
