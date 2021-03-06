/**
 * This file was automatically generated by the Mule ESB Maven Tools
 */
package com.mdw360.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.transport.NullPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MuleBootstrap.class)
@IntegrationTest
public class ExampleFunctionalTestCase extends SprintBootMuleFunctionalTestCase {


    private static final Logger log = LoggerFactory.getLogger(ExampleFunctionalTestCase.class);

    @Override
    protected String getConfigFile()
    {
        return "mule-config.xml";
    }

    @Test
    public void testSanity(){
        log.info("We are still here!");
    }

    @Test
    public void testConfiguration() throws Exception
    {
    	MuleClient client = muleContext.getClient();
        MuleMessage message = new DefaultMuleMessage("some data", muleContext);
        client.dispatch("vm://in", message);
        MuleMessage result = client.request("vm://out", RECEIVE_TIMEOUT);
        assertNotNull(result);
        assertNull(result.getExceptionPayload());
        assertFalse(result.getPayload() instanceof NullPayload);

        //TODO Assert the correct data has been received
        assertEquals("some data Received", result.getPayloadAsString());
    }
}
