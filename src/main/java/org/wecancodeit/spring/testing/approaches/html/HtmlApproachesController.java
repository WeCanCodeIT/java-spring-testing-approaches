package org.wecancodeit.spring.testing.approaches.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HtmlApproachesController {

	@RequestMapping
	public String sayHello(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
}
