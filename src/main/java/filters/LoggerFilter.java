package filters;

import javax.servlet.*;
import java.io.IOException;

public class LoggerFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before");
        chain.doFilter(request, response);
        System.out.println("After");
    }

    @Override
    public void destroy() {

    }
}
