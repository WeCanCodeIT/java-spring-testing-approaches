package org.wecancodeit.spring.testing.approaches;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@RequestMapping
	public String sayHello(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
}
