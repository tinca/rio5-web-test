package test;

import net.jini.core.lookup.ServiceTemplate;
import net.jini.discovery.DiscoveryManagement;
import net.jini.discovery.LookupDiscovery;
import net.jini.lookup.ServiceDiscoveryEvent;
import net.jini.lookup.ServiceDiscoveryListener;
import net.jini.lookup.ServiceDiscoveryManager;
import net.jini.space.JavaSpace;

import java.io.IOException;
import java.security.Policy;
import java.util.logging.Logger;

/**
 *
 */
public class ServiceManager {
    static final private String NAME = ServiceManager.class.getName();
    static final private Logger LOG = Logger.getLogger(NAME);

    private ServiceDiscoveryManager sdm;
    private DiscoveryManagement discoveryManagement;
    private String[] groups;
    private JavaSpace space;

    public ServiceManager(String[] groups) throws IOException {
        this.groups = groups;
        init();
    }

    private void init() throws IOException {
        StringBuilder info = new StringBuilder();
        info.append("=====================================\n");
        info.append("Security manager: ").append(System.getSecurityManager()).append("\n");
        info.append("Policy: ").append(Policy.getPolicy()).append("\n");
        info.append("groups: ").append(groups[0]).append("\n");
        info.append("=====================================");
        LOG.info(info.toString());
        discoveryManagement = new LookupDiscovery(groups);
        sdm = new ServiceDiscoveryManager(discoveryManagement, null);
        ServiceTemplate tmpl = new ServiceTemplate(null, new Class[]{JavaSpace.class}, null);
        sdm.createLookupCache(tmpl, null, createServiceDiscoveryListener());
    }

    private ServiceDiscoveryListener createServiceDiscoveryListener() {
        return new ServiceDiscoveryListener() {

            @Override
            public void serviceAdded(ServiceDiscoveryEvent e) {
                Object service = e.getPostEventServiceItem().service;
                LOG.entering(NAME, "serviceAdded", service);
                if (service instanceof JavaSpace) {
                    space = (JavaSpace)service;
                }
            }

            @Override
            public void serviceRemoved(ServiceDiscoveryEvent e) {
                Object service = e.getPreEventServiceItem().service;
                LOG.entering(NAME, "serviceRemoved", service);
            }

            @Override
            public void serviceChanged(ServiceDiscoveryEvent e) {
                // OK
            }
        };
    }

    public JavaSpace getSpace() {
        return space;
    }
}
