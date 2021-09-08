package nagarro.exitassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * map all the unmatched request to the home view
 * @author vikantbhati
 *
 */
@Controller
public class RequestForwardController {
	
	/**
	 * map to home view incase of no request matched
	 */
    @GetMapping(value = "/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}