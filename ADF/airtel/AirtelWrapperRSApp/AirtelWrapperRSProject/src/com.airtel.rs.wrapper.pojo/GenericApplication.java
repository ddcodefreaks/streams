package com.airtel.rs.wrapper.pojo;

import com.airtel.rs.wrapper.email.AirtelEmailWrapper;
import com.airtel.rs.wrapper.nsdl.PanVerification;
import com.airtel.rs.wrapper.sms.AirtelSMSWrapper;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class GenericApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register root resources.
        classes.add(PanVerification.class);
        classes.add(AirtelSMSWrapper.class);
        classes.add(AirtelEmailWrapper.class);

        // Register provider classes.

        return classes;
    }
}
