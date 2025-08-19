package com.example.weather_app.Controller;

import com.example.weather_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    @Value("${api.key}")
    private String apikey;

    @GetMapping("/")
    public String getIndex(){
        return "Index";
    }

    @GetMapping("/weather")
    public String city(@RequestParam String city, Model model){
        String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apikey+"&units=metric";
        RestTemplate rt = new RestTemplate();
        WeatherResponse wr = rt.getForObject(url,WeatherResponse.class);

        if(wr != null){
            model.addAttribute("city",wr.getName());
            model.addAttribute("country",wr.getSys().getCountry());
            model.addAttribute("weatherDescription",wr.getWeather().get(0).getDescription());
            model.addAttribute("temperature",wr.getMain().getTemp());
            model.addAttribute("humidity",wr.getMain().getHumidity());
            model.addAttribute("windSpeed",wr.getWind().getSpeed());
        }
        return "weather";
    }
}
