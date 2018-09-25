package sergio.ru.insurancetest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InsuranceController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/contract", method = RequestMethod.POST)
    public String sayHello(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "contract";
    }
}
