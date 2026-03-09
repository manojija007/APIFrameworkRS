package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.AddPlaceLocation;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("9566504567");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		ArrayList<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		p.setTypes(list);
		AddPlaceLocation l = new AddPlaceLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlacePayload(String place_id) {

		return "{\r\n" +
				"\"place_id\":\""+place_id+"\"\r\n" +
				"}";
		
	}
}
