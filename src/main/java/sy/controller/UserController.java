package sy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import sy.model.User;
import sy.service.UserServiceI;

@Controller
@RequestMapping("user")
public class UserController {

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@RequestMapping("showuser")
	public String showUser(String id, HttpServletRequest request) {
		
		System.out.println(id);
		User u = userService.getUserById(id);
		request.setAttribute("user", u);
		return "showUser";
	}
	
	@RequestMapping(value = "/{id}/show4json",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String showUser4json(@PathVariable String id, HttpServletRequest request) {
		System.out.println(id);
		User u = userService.getUserById(id);
		request.setAttribute("user", u);
		return JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss");
	}
	

}
