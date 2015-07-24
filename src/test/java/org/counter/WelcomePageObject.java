package org.counter;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("/")
public class WelcomePageObject {

    @FindBy(id = "message")
    private WebElement message;

    public String getMessage() {
        return this.message.getText();
    }
}
