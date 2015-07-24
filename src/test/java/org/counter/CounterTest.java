package org.counter;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

@RunWith(Arquillian.class)
public class CounterTest {

    @Deployment(testable = false)
    public static GenericArchive createNodeJsApplication() {
        return ShrinkWrap.create(GenericArchive.class, "app.tar")
                .add(new FileAsset(new File("src/main/js/index.js")), "index.js")
                .add(new FileAsset(new File("src/main/js/package.json")), "package.json");
    }

    @Test
    public void shouldReturnMessageFromNodeJs(@ArquillianResource URL base) {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                base.openStream()));) {
            String userInput = in.readLine();
            System.out.println(userInput);
        } catch (UnknownHostException e) {
            fail("Don't know about host ");
        } catch (IOException e) {
            fail("Couldn't get I/O for the connection to ");
        }

    }

}
