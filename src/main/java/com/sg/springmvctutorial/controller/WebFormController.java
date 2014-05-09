package com.sg.springmvctutorial.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sg.springmvctutorial.domain.TravelTrip;
import com.sg.springmvctutorial.repo.TravelTripDao;

@Controller
public class WebFormController {
	
	@Autowired
	private TravelTripDao travelTripDao;
	
	private boolean firstTime = true;
	
	@RequestMapping(value = "/myTravels", method = RequestMethod.GET)
	public String displaySortedTravelTrips(Model model){
		if(firstTime){
			CreateDummyTravelTrip();
		}
		//model.addAttribute("newTravelTrip",new TravelTrip());
		model.addAttribute("travelTripList", travelTripDao.findAll());
		return "index";
	}
	
	@RequestMapping(value = "/addTravelTripForm", method = RequestMethod.GET)
	public String addTravelTripForm(Model model){
		model.addAttribute("newTravelTrip",new TravelTrip());
		return "addTravelTrip";
	}
	
	private void CreateDummyTravelTrip(){
		TravelTrip travelTripTemp = new TravelTrip();
		travelTripTemp.setCity("Chennai");
		travelTripTemp.setCountry("India");
		travelTripTemp.setBusiness(false);
		travelTripTemp.setFromDate("01/02/2013");
		travelTripTemp.setToDate("05/02/2013");
		travelTripDao.register(travelTripTemp);
		
		travelTripTemp = new TravelTrip();
		travelTripTemp.setCity("EdinBerg");
		travelTripTemp.setCountry("Australia");
		travelTripTemp.setBusiness(true);
		travelTripTemp.setFromDate("05/05/2014");
		travelTripTemp.setToDate("05/05/2015");
		travelTripDao.register(travelTripTemp);
	}
	
	@RequestMapping(value="addTravelTrip",method = RequestMethod.POST)
	public String registerNewTravelTrip(@Valid @ModelAttribute("newTravelTrip") TravelTrip newTravelTrip,
			BindingResult result,Model model){
		if(!result.hasErrors()){
			travelTripDao.register(newTravelTrip);
			//return "redirect:/";
			//model.addAttribute("newTravelTrip",new TravelTrip());
		}else {
			model.addAttribute("travelTripList", travelTripDao.findAll());	  
	    }
		model.addAttribute("travelTripList",travelTripDao.findAll());		
		
		return "index";
	}
	
	@RequestMapping(value="/{tripID}/deleteTravelTrip", method = RequestMethod.GET)
	public String deleteTravelTrip(@PathVariable("tripID") String tripID,Model model){
		travelTripDao.delete(Integer.parseInt(tripID));
		model.addAttribute("travelTripList", travelTripDao.findAll());
		//return "redirect:/";
		return "index";
	}
	
	@RequestMapping(value="/{tripID}/editTravelTripForm", method = RequestMethod.GET)
	public String editTravelTripForm(@PathVariable("tripID") String tripID,Model model){
		TravelTrip tempTravel = travelTripDao.findById(Integer.parseInt(tripID));
		if(tempTravel == null){
			return "index";
		}
		model.addAttribute("editTravelTrip",tempTravel);
		return "/editTravelTrip";
	}
	
	@RequestMapping(value="/{tripID}/editTravelTrip", method = RequestMethod.POST)
	public String editTravelTrip(@PathVariable("tripID") String tripID,
			@Valid @ModelAttribute("editTravelTrip") TravelTrip editTravelTrip,
			BindingResult result, Model model){
		TravelTrip tempTravelTrip = travelTripDao.findById(Integer.parseInt(tripID));
		tempTravelTrip.setCountry(editTravelTrip.getCountry());
		tempTravelTrip.setCity(editTravelTrip.getCity());
	    tempTravelTrip.setFromDate(editTravelTrip.getFromDate());
	    tempTravelTrip.setToDate(editTravelTrip.getToDate());
	    tempTravelTrip.setBusiness(editTravelTrip.isBusiness());
	    
	    model.addAttribute("travelTripList",travelTripDao.findAll());
		//return "redirect:/";
	    return "index";
	}
	
	@RequestMapping(value="/{tripID}/detailsTravelTrip",method = RequestMethod.GET)
	public String detailsTravelTrip(@PathVariable("tripID") String tripID,Model model){
		TravelTrip tempTravelTrip = travelTripDao.findById(Integer.parseInt(tripID));
		model.addAttribute("detailsTravelTrip", tempTravelTrip);
		return "detailsTravelTrip";
	}
	
}
