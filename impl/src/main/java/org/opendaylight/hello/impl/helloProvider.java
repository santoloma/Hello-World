/*
 * Copyright © 2016 Cisco Systems and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.hello.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.RpcRegistration;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.hello.rev150105.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class helloProvider {

    private static final Logger LOG = LoggerFactory.getLogger(helloProvider.class);

    private final DataBroker dataBroker;
    private RpcRegistration<HelloService> serviceRegistration;
    private RpcProviderRegistry rpcProviderRegistry;

    public helloProvider(final DataBroker dataBroker, RpcProviderRegistry rpcProviderRegistry) {
        this.dataBroker = dataBroker;
        this.rpcProviderRegistry = rpcProviderRegistry;
    }


    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
         serviceRegistration = rpcProviderRegistry.addRpcImplementation(HelloService.class, new HelloWorldImpl());
         LOG.info("helloProvider Session Initiated");
         System.out.println("xyz");
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        serviceRegistration.close();
        LOG.info("helloProvider Closed");
    }
}
