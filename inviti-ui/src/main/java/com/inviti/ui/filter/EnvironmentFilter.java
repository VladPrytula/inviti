package com.inviti.ui.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Sergii_Iakymenko on 11/08/2014.
 */
@WebFilter(filterName = "EnvironmentFilter", urlPatterns = {"/*"})
public class EnvironmentFilter implements Filter {

    private static final String ENVIRONMENT_PROPERTIES = "com/inviti/ui/inviti-ui-%s.properties";
    private static final String ENVIRONMENT_NAME = "environment";

    private Properties properties;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String restUrl = properties.getProperty("inviti.rest.url");

        req.setAttribute(ENVIRONMENT_NAME, restUrl);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String env = System.getProperty(ENVIRONMENT_NAME, "local").toLowerCase();
        properties = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(String.format(ENVIRONMENT_PROPERTIES, env))) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
