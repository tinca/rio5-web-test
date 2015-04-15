package test;

import net.jini.core.entry.Entry;
import net.jini.core.lease.Lease;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class TestServlet extends HttpServlet {
    static final private String NAME = TestServlet.class.getName();
    static final private Logger LOG = Logger.getLogger(NAME);
    private ServiceManager serviceManager;

    @Override
    public void init() throws ServletException {
        LOG.entering(getClass().getName(), "init");
        try {
            serviceManager = new ServiceManager(new String[]{"kuti"});
            LOG.info("service manager created");
        }
        catch (IOException e) {
            LOG.log(Level.SEVERE, "", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(String.format("<h2> %s : %s</h2>", callSpace(), new Date()));
    }

    private String callSpace() {
        if (serviceManager.getSpace() == null) {
            return "Space is not available";
        }

        try {
            serviceManager.getSpace().write(new TestEntry(), null, Lease.FOREVER);
            return "Entry is written";
        }
        catch (Throwable t) {
            LOG.log(Level.SEVERE, "", t);
            return t.getMessage();
        }
    }

    public static class TestEntry implements Entry {

    }
}
