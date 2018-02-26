package org.wecancodeit.spring.testing.approaches.integrationtests.mvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.spring.testing.approaches.GreetingController;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerMockMvcTests {

	@Resource
	private MockMvc mvc;

	/**
	 * Verify that the response status is 200 (OK).
	 */
	@Test
	public void shouldBeOk() throws Exception {
		mvc.perform(get("/sayHello?name=George")).andExpect(status().isOk());
	}

	/**
	 * Verify that the view we are using is what's expected. In this case, we are
	 * using Thymeleaf and our view template is
	 * <code>src/main/resources/templates/hello.html</code>.
	 */
	@Test
	public void shouldReturnExpectedView() throws Exception {
		mvc.perform(get("/sayHello?name=George")).andExpect(view().name(is("hello")));
	}

	/**
	 * Verifies that our model contains a "name" attribute and that its value is "George".
	 * @throws Exception
	 */
	@Test
	public void shouldPutNameInModel() throws Exception {
		mvc.perform(get("/sayHello?name=George")).andExpect(model().attribute("name", is("George")));
	}

	/**
	 * <p>
	 * Verify that the html returned contains the expected content. This is less
	 * useful for html than for endpoints that return JSON or XML.
	 * </p>
	 * 
	 * <p>
	 * We could have used any Hamcrest {@link Matcher} that accepts a {@link String}
	 * in place of {@link Matchers#containsString(String)}.
	 * </p>
	 */
	@Test
	public void shouldContainExpectedContent() throws Exception {
		mvc.perform(get("/sayHello?name=George")).andExpect(content().string(containsString("Hello, George!")));
	}

	/**
	 * You may see these one or more of these tests combined, like so.
	 */
	@Test
	public void shouldGreet() throws Exception {
		mvc.perform(get("/sayHello?name=George")).andExpect(status().isOk()).andExpect(view().name(is("hello")))
				.andExpect(content().string(containsString("Hello, George!")));
	}


}
