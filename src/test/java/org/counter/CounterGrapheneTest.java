package org.counter;

import static org.assertj.core.api.Assertions.*;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.URL;

@RunWith(Arquillian.class)
public class CounterGrapheneTest {

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL contextRoot;

    @Deployment(testable = false)
    public static GenericArchive createNodeJsApplication() {
        return ShrinkWrap.create(GenericArchive.class, "app.tar")
                .add(new FileAsset(new File("src/main/js/index.js")), "index.js")
                .add(new FileAsset(new File("src/main/js/package.json")), "package.json");
    }

    @Test
    public void shouldPrintWelcomeMessage(@InitialPage WelcomePageObject loginPage ) {
        assertThat(loginPage.getMessage()).isEqualTo("Hello from inside a container!");
    }

}
