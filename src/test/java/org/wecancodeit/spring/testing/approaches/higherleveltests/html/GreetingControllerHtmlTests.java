package org.wecancodeit.spring.testing.approaches.higherleveltests.html;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.spring.testing.approaches.GreetingController;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//TODO we can do much more complex things, should we illustrate with an example?
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerHtmlTests {

	@Resource
	private WebClient webClient;

	@Test
	public void shouldGreet() throws IOException {

		HtmlPage response = webClient.getPage("/sayHello?name=George");

		DomNode elementWithMessageId = response.querySelector(".message"); // this is a CSS selector
		assertThat(elementWithMessageId.getTextContent(), is("Hello, George!"));
	}
}
