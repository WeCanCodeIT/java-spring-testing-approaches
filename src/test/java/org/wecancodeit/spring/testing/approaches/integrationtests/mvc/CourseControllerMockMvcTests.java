package org.wecancodeit.spring.testing.approaches.integrationtests.mvc;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.spring.testing.approaches.Course;
import org.wecancodeit.spring.testing.approaches.CourseController;
import org.wecancodeit.spring.testing.approaches.CourseRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
public class CourseControllerMockMvcTests {

	@Resource
	private MockMvc mvc;

	/**
	 * In addition to creating a Mockito mock like @{@link Mock}, @{@link MockBean}
	 * both creates a mock and adds/replaces it to the Spring context.
	 */
	@MockBean
	private CourseRepository repository;

	@Mock // doesn't need to be @MockBean
	private Course course;

	@Mock
	private Course anotherCourse;

	/**
	 * Probably best to verify the view is being returned first, since none of this
	 * other stuff will work until we return a valid view (template) name.
	 */
	@Test
	public void shouldRouteToSingleCourseView() throws Exception {
		mvc.perform(get("/course?id=42")).andExpect(view().name(is("single-course-view")));
	}

	@Test
	public void shouldBeOkForSingleCourse() throws Exception {
		mvc.perform(get("/course?id=42")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutSingleCourseIntoModel() throws Exception {
		when(repository.findOne(42)).thenReturn(course);

		mvc.perform(get("/course?id=42")).andExpect(model().attribute("selectedCourse", is(course)));
	}
	
	@Test
	public void shouldRouteToAllCoursesView() throws Exception {
		mvc.perform(get("/courses")).andExpect(view().name(is("all-courses-view")));
	}
	
	@Test
	public void shouldBeOkForAllCourses() throws Exception {
		mvc.perform(get("/courses")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllCoursesIntoModel() throws Exception {
		Collection<Course> allCourses = asList(course, anotherCourse);
		when(repository.findAll()).thenReturn(allCourses);

		mvc.perform(get("/courses")).andExpect(model().attribute("courses", is(allCourses)));
	}
}
