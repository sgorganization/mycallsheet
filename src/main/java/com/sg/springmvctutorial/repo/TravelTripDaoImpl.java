package com.sg.springmvctutorial.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sg.springmvctutorial.domain.TravelTrip;

@Repository
public class TravelTripDaoImpl implements TravelTripDao {

	private int idCounter=1;
	List<TravelTrip> travelTripList = new ArrayList<TravelTrip>();
	
	@Override
	public TravelTrip findById(int id) {
		for(TravelTrip travelTripTemp:travelTripList){
			if(travelTripTemp.getId() == id){
				return travelTripTemp;
			}
		}
		return null;
	}

	@Override
	public List<TravelTrip> findType(boolean typeBusiness) {
		List<TravelTrip> travelTripReturnList = new ArrayList<TravelTrip>();
		
		for(TravelTrip travelTripTemp:travelTripList){
			if(travelTripTemp.isBusiness() == typeBusiness){
				travelTripReturnList.add(travelTripTemp);
			}
		}
		
		return travelTripReturnList;
	}

	@Override
	public List<TravelTrip> findAll() {
		return travelTripList;
	}

	@Override
	public void register(TravelTrip travelTripObj) {
		travelTripObj.setId(idCounter++);
		travelTripList.add(travelTripObj);
	}
	
	@Override
	public void delete(int id){
		TravelTrip travelTripTemp = findById(id);
		if(travelTripTemp==null){
			return;
		}else{
			travelTripList.remove(travelTripTemp);
		}
	}

}
