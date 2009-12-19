package ${package};


import org.apache.wicket.util.tester.WicketTester;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.wicket.spring.test.ApplicationContextMock;
import org.springframework.context.ApplicationContext;


/**
 * Base class for Wicket unit tests. Allows Wicket pages and components to be
 * easily tested in isolation. For pages or components that rely on Spring
 * dependency injection, consider overriding {@link #initSpringContext}.
 */
public abstract class BaseWicketUnitTest
{
    protected WicketTester _tester;
    
    @Before
    public void createTester()
    {
        _tester = new WicketTester(new ${app_classname}() {
            @Override public String getConfigurationType()
            {
                // Don't test in development mode, since debug utilities
                // can break XHTML compliance.
                return DEPLOYMENT;
            }
            @Override protected ApplicationContext getApplicationContext()
            {
                // Provide a static Spring context that can be configured
                // with mock beans for testing purposes.
                ApplicationContextMock c = new ApplicationContextMock();
                initSpringContext(c);
                return c;
            }
        });
    }
    
    @After
    public void destroyTester()
    {
        _tester.destroy();
    }
    
    /**
     * Subclasses should override this method to register mock Spring beans
     * in the Spring context. This will allow Wicket components that contain
     * SpringBean annotations to use these mock beans during testing.
     * The default implementation of this method is empty.
     * Example usage:
     * <pre>
     * protected void initSpringContext(ApplicationContextMock ctx)
     * {
     *     MyService svc = // init mock service bean
     *     ctx.putBean(svc);
     * }
     * </pre>
     */
    protected void initSpringContext(ApplicationContextMock ctx)
    {
        // pass
    }
}