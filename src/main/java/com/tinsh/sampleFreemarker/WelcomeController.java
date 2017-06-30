package com.tinsh.sampleFreemarker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	Logger logger= Logger.getLogger(this.getClass());
	
	@RequestMapping("/")
	protected ModelAndView getHome(HttpServletRequest request,
			HttpServletResponse response , ModelAndView model){
		System.out.println("In the home page");
		logger.debug("In the home page");
		model.addObject("home",false);
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(path="/login" ,method=RequestMethod.POST)
	protected ResponseEntity<User> login(@RequestBody User user, ModelAndView model) throws Exception{
		System.out.println("In the login page.");
		System.out.println("userName :"+user.getUserName());
		System.out.println("password :"+user.getPassword());
		ResponseEntity<User> response;
		if(isUserValid(user.getUserName(),user.getPassword())){
			System.out.println("user is authenticated.");
			model.addObject("home",true);
			model.addObject("userName", user.getUserName());
			model.setViewName("home");
			response= new ResponseEntity<>(user,HttpStatus.OK);  
		}else{
			response= new ResponseEntity<>(user,HttpStatus.INTERNAL_SERVER_ERROR);  
		}
		return response;
	}
	
	
	@RequestMapping(path="/home" ,method=RequestMethod.GET)
	protected ModelAndView getWelcomePage(ModelAndView model, String userName) throws Exception{
		System.out.println("In the home page.");
		
		model.addObject("home",true);
		model.addObject("name",userName);
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(path="/home" ,method=RequestMethod.POST)
	protected ModelAndView getWelcomePage(ModelAndView model, @RequestBody User user) throws Exception{
		System.out.println("In the home page.");
		
		model.addObject("home",true);
		model.addObject("name",user.getUserName());
		model.setViewName("home");
		return model;
	}
	
	public static Connection getConnection() throws Exception {
		 Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Datahub\\Desktop\\Tinsh\\Project\\DB\\GrabBreakfast_DB.accdb");
		 return conn;
	 }
	
	@RequestMapping(path="/getcities" ,method=RequestMethod.GET)
	protected ResponseEntity<List<City>> getCities(ModelAndView model) throws Exception{
		System.out.println("In the getCities.");
		ResponseEntity<List<City>> response=null;

		List<City> cities=getCityList();
		
		response= new ResponseEntity<>(cities,HttpStatus.OK);  
		model.addObject("home",true);
		model.setViewName("home");

		return response;
	}
	
	@RequestMapping(path="/getareas" ,method=RequestMethod.POST)
	protected ResponseEntity<List<Area>> getAreas(ModelAndView model, @RequestBody String cityId) throws Exception{
		System.out.println("In the getAreas :"+cityId);
		ResponseEntity<List<Area>> response=null;

		List<Area> cities=getAreaList(cityId);
		
		response= new ResponseEntity<>(cities,HttpStatus.OK);  
		model.addObject("home",true);
		model.setViewName("home");

		return response;
	}
	
	@RequestMapping(path="/getrestos" ,method=RequestMethod.POST)
	protected ResponseEntity<List<Restaurant>> getRestaurants(ModelAndView model, 
			@RequestBody String areaId) throws Exception{
		System.out.println("In the getRestaurants :"+areaId);
		ResponseEntity<List<Restaurant>> response=null;

		List<Restaurant> restos=getRestaurantList(areaId);
		
		response= new ResponseEntity<>(restos,HttpStatus.OK);  
		model.addObject("home",true);
		model.setViewName("home");

		return response;
	}
	
	
	@RequestMapping(path="/getdishes" ,method=RequestMethod.POST)
	protected ResponseEntity<List<Dish>> getDishes(ModelAndView model, 
			@RequestBody String restoId) throws Exception{
		System.out.println("In the getDishes :"+restoId);
		ResponseEntity<List<Dish>> response=null;

		List<Dish> restos=getDishList(restoId);
		
		response= new ResponseEntity<>(restos,HttpStatus.OK);  
		model.addObject("home",true);
		model.setViewName("home");

		return response;
	}
	
	 private static List<City> getCityList() throws Exception {
		 	Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		    List<City> cities= new ArrayList<City>();
		    
		    conn = getConnection();
		    stmt = conn.createStatement();
		 
		    String excelQuery = "select id,cityname from city";
		    rs = stmt.executeQuery(excelQuery);
		    
		    while (rs.next()) {
		    	System.out.println("cityname :"+rs.getString("cityname"));
		    	City city= new City();
		    	city.setCityId(rs.getString("id"));
		    	city.setCityName(rs.getString("cityname"));
		    	cities.add(city);
		    }
		 
		 return cities;
	}

	 
	 private static List<Area> getAreaList(String cityId) throws Exception {

		 	Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		    List<Area> areas= new ArrayList<Area>();
		    
		    conn = getConnection();
		    stmt = conn.createStatement();
		 
		    String excelQuery = "select id,areaname from area where cityid="+cityId;
		    rs = stmt.executeQuery(excelQuery);
		    
		    while (rs.next()) {
		    	System.out.println("areaname :"+rs.getString("areaname"));
		    	Area area= new Area();
		    	area.setAreaId(rs.getString("id"));
		    	area.setAreaName(rs.getString("areaname"));
		    	areas.add(area);
		    }
		 
		 return areas;
	}
	 
	 private static List<Restaurant> getRestaurantList(String areaId) throws Exception {

		 	Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		    List<Restaurant> restaurants= new ArrayList<Restaurant>();
		    
		    conn = getConnection();
		    stmt = conn.createStatement();
		 
		    String excelQuery = "select id,restoname from restaurant where areaid="+areaId;
		    rs = stmt.executeQuery(excelQuery);
		    
		    while (rs.next()) {
		    	System.out.println("restoname :"+rs.getString("restoname"));
		    	Restaurant resto= new Restaurant();
		    	resto.setRestId(rs.getString("id"));
		    	resto.setRestName(rs.getString("restoname"));
		    	restaurants.add(resto);
		    }
		 
		 return restaurants;
	}
	 
	public static List<Dish> getDishList(String restoId) throws Exception {
		    Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;

		    List<Dish> dishes= new ArrayList<Dish>();
		    
		    conn = getConnection();
		    stmt = conn.createStatement();
		    String excelQuery = "select id,dishname,price from foodmenu where restoid="+restoId;
		    rs = stmt.executeQuery(excelQuery);

		    while (rs.next()) {
		    	Dish dish= new Dish();
		    	System.out.println("dishname :"+rs.getString("dishname"));
		    	dish.setDishId(rs.getString("id"));
		    	dish.setDishName(rs.getString("dishname"));
		    	dish.setDishPrice(rs.getString("price"));
		    	dishes.add(dish);
		    }

		    rs.close();
		    stmt.close();
		    conn.close();
		    
		    return dishes;
		  }
	 
	 private static boolean isUserValid(String userName,String password) throws Exception{
		 	Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;

		    conn = getConnection();
		    stmt = conn.createStatement();
		    String excelQuery = "select username,password from login";
		    rs = stmt.executeQuery(excelQuery);

		    while (rs.next()) {
		    	System.out.println("username :"+rs.getString("username"));
		    	if(rs.getString("username").equals(userName) && rs.getString("password").equals(password)){
		    		rs.close();
		  		    stmt.close();
		  		    conn.close();
		    		return true;
		    	}
		    }
		 return false;
	 }
	
	/* public static void main(String []args) throws Exception{
		 //getCityList();
		 getArea("1");
	 }*/
	 
}
