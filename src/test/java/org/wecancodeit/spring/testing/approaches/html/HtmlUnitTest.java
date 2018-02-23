package org.wecancodeit.spring.testing.approaches.html;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(SpringRunner.class)
@WebMvcTest(HtmlApproachesController.class)
public class HtmlUnitTest {

	@Resource
	private WebClient webClient;
	
	@Test
	public void shouldReceiveExpectedHtml() throws IOException {
		
		String uri = "/sayHello?name=George";
		
		HtmlPage response = webClient.getPage(uri);
		//TODO we can do much more complex things, need an example of using xpath or the like
		
		// this is a CSS selector
		String firstParagraphContent = response.querySelector(".message").getTextContent();
		
		assertThat(firstParagraphContent, is("Hello, George!"));
	}
}
