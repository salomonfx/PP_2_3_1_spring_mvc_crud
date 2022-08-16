package web.config;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { return null; }

    @Override
    protected Class<?>[] getServletConfigClasses() { return new Class[]{WebConfig.class}; }

    @Override
    protected String[] getServletMappings() { return new String[]{"/"}; }

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
        registerCharacterEncodingFilter(context);
    }

    private void registerCharacterEncodingFilter(ServletContext context) {
        EnumSet<DispatcherType> dispatcherTypes =
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);

        CharacterEncodingFilter characterEncodingFilter =
                new CharacterEncodingFilter("UTF-8", true, true);

        context.addFilter("encoding", characterEncodingFilter)
                .addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}