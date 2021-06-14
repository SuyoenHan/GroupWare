package com.t1works.groupware.ody.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 
@Component
@Controller
public class WeatherOdyController {
	 // >>> #193. 기상청 공공데이터(오픈데이터)를 가져와서 날씨정보 보여주기 <<< //
    @RequestMapping(value="/opendata/weatherXML.tw", method= {RequestMethod.GET}) 
    public String weatherXML() {
       return "ody/weatherXML";
       
    }
}
