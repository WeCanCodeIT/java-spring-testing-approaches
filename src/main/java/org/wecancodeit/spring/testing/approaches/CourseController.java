package org.wecancodeit.spring.testing.approaches;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

	@Resource
	private CourseRepository repository;
	
	@RequestMapping("/course")
	public String showCourse(@RequestParam("id") long courseId, Model model) {
		Course selected = repository.findOne(courseId);
		model.addAttribute("selectedCourse", selected);
		return "single-course-view";
	}

	@RequestMapping("/courses")
	public String showAll(Model model) {
		Collection<Course> allCourses = repository.findAll();
		
		model.addAttribute("courses", allCourses);
		
		return "all-courses-view";
	}

}
