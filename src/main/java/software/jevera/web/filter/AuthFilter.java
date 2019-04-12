package software.jevera.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        if (!requestURI.startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }
        if (httpRequest.getSession() != null && httpRequest.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
            return;
        }
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
    }
}
