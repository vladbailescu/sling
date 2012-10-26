/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.commons.log.internal.slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import org.apache.sling.commons.log.internal.config.GlobalConfigurationTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;

public class SlingConfigurationPrinterTest extends GlobalConfigurationTest {

    @Mock
    private BundleContext context;
    
    public SlingConfigurationPrinterTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSlingLogConfigurationPrinter() throws ConfigurationException {
        testGlobalConfigorator();
        SlingConfigurationPrinter p = new SlingConfigurationPrinter();
        SlingConfigurationPrinter.registerPrinter(context);
        URL[] u = p.getAttachments("zip");
        Assert.assertNotNull(u);
        Assert.assertEquals(u.length,1);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        p.printConfiguration(printWriter);
        SlingConfigurationPrinter.unregisterPrinter();
        Assert.assertTrue(stringWriter.toString().length() > 0);
    }

}
