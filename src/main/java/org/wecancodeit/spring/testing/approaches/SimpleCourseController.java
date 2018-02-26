package org.wecancodeit.spring.testing.approaches;

import java.util.Collection;

import org.springframework.ui.Model;

public class SimpleCourseController {

	private CourseRepository repository;
	
	public String showCourse(long courseId, Model model) {
		Course selected = repository.findOne(courseId);
		model.addAttribute("selectedCourse", selected);
		return "single-course-view";
	}

	public void showAll(Model model) {
		Collection<Course> allCourses = repository.findAll();
		
		model.addAttribute("courses", allCourses);
	}

}
