/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Channel;
import org.apache.camel.Endpoint;
import org.apache.camel.Route;
import org.apache.camel.Service;

/**
 * A <a href="http://camel.apache.org/routes.html">Route</a>
 * defines the processing used on an inbound message exchange
 * from a specific {@link org.apache.camel.Endpoint} within a {@link org.apache.camel.CamelContext}
 *
 * @version $Revision$
 */
public abstract class DefaultRoute implements Route {

    private final Map<String, Object> properties = new HashMap<String, Object>();
    private Endpoint endpoint;
    private List<Service> services = new ArrayList<Service>();
    private List<Channel> channels = new ArrayList<Channel>();

    public DefaultRoute(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public DefaultRoute(Endpoint endpoint, Service... services) {
        this(endpoint);
        for (Service service : services) {
            addService(service);
        }
    }

    @Override
    public String toString() {
        return "Route";
    }

    public String getId() {
        return (String) properties.get(Route.ID_PROPERTY);
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public List<Service> getServicesForRoute() throws Exception {
        List<Service> servicesForRoute = new ArrayList<Service>(getServices());
        addServices(servicesForRoute);
        return servicesForRoute;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addService(Service service) {
        getServices().add(service);
    }

    /**
     * Strategy method to allow derived classes to lazily load services for the route
     */
    protected void addServices(List<Service> services) throws Exception {
    }

}
