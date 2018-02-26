package org.wecancodeit.spring.testing.approaches;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CourseControllerTest {

	@InjectMocks
	private CourseController underTest;

	@Mock
	private CourseRepository repository;
	
	@Mock
	private Course course;
	
	@Mock
	private Course anotherCourse;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCourseToModel() {
		long arbitraryCourseId = 42;
		when(repository.findOne(arbitraryCourseId)).thenReturn(course);
		
		underTest.showCourse(arbitraryCourseId, model);
		
		verify(model).addAttribute("selectedCourse", course);
	}
	
	/**
	 * I might do this in my MockMvc test instead.
	 */
	@Test
	public void shouldReturnSingleCourseView() {
		String templateName = underTest.showCourse(42L, model);
		
		assertThat(templateName, is("single-course-view"));
	}
	
	@Test
	public void shouldAddAllCoursesToModel() {
		Collection<Course> allCourses = asList(course, anotherCourse);
		when(repository.findAll()).thenReturn(allCourses);
		
		underTest.showAll(model);
		
		verify(model).addAttribute("courses", allCourses);
	}
}
