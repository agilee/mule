/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.jdbc.functional;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertThat;

public class JdbcEndpointSessionPropertiesEe2323TestCase extends AbstractJdbcFunctionalTestCase
{

    public JdbcEndpointSessionPropertiesEe2323TestCase(ConfigVariant variant, String configResources)
    {
        super(variant, configResources);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters()
    {
        return Arrays.asList(new Object[][]{
                {ConfigVariant.FLOW, AbstractJdbcFunctionalTestCase.getConfig() + ",jdbc-endpoint-session-properties.xml"}
        });
    }

    @Test
    public void testSelectOnOutbound() throws Exception
    {
        MuleClient client = new MuleClient(muleContext);
        MuleMessage response = client.send("vm://Flow1s1", new DefaultMuleMessage(new Object(), muleContext));
        assertThat(response, IsNull.<Object>notNullValue());
        assertThat(response.getExceptionPayload(), IsNull.<Object>nullValue());
    }

}
