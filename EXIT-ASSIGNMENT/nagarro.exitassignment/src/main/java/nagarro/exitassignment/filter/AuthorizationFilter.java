package nagarro.exitassignment.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import nagarro.exitassignment.utility.TokenUtility;

/**
 * filter that check weather the token is valid or not, and allow the access all
 * the API related to verified user, if it is VALID USER
 * 
 * @author vikantbhati
 *
 */
@Component
@CrossOrigin
public class AuthorizationFilter implements Filter {

	@Autowired
	public TokenUtility util;

	/**
	 * method to perform filteration
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin,X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method, Authorization");

		String token = req.getHeader("Authorization");

		if (req.getMethod().equals("OPTIONS")) {
			res.setStatus(200);
		} else if (util.isValidToken(token)) {
			chain.doFilter(request, response);
		} else {
			res.sendError(401);
		}
	}
}
