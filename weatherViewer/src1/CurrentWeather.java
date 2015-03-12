package ca.uwo.csd.cs2212.team17

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//need import json_lib and org.json.jar
public class CurrentWeather {

	private String City, Sky, Sunrise, Sunset, Country;
	private double Temp, Pressure, Humidity, TempMax, TempMin, WindSpeed,
			WindDir;

	public CurrentWeather(String CityName) throws UnsupportedEncodingException {

		// URL open weather map

		StringBuffer SBR;

		// get weather information from http://api.openweathermap.org for given
		// city
		// 65da394090951035f3a346d9a356ddd9 api key from OpenWeatherMap
		String OwmUrl = "http://api.openweathermap.org/data/2.5/weather?q="
				+ CityName + "&APPID=65da394090951035f3a346d9a356ddd9";

		// get current weather data from openweathermap.org in JSON format
		SBR = new StringBuffer();

		try {
			URL url = new URL(OwmUrl);
			URLConnection connection = url.openConnection();
			BufferedReader BReader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			while ((line = BReader.readLine()) != null)
				SBR.append(line + " ");
			BReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String WeatherData = SBR.toString();

		// parse JSON data, JSON_Data:WeatherData

		JSONObject JsonData = JSONObject.fromObject(WeatherData);
		// Save all weather information��

		// get information from sys object
		JSONObject Obj_Sys = JsonData.getJSONObject("sys");// country
		Country = Obj_Sys.getString("country");
		Sunrise = Obj_Sys.getString("sunrise");
		Sunset = Obj_Sys.getString("sunset");

		// get information from string
		String City = JsonData.getString("name");// city

		// get information from main object
		JSONObject Obj_Main = JsonData.getJSONObject("main");
		Temp = Obj_Main.getDouble("temp");// temperature
		Pressure = Obj_Main.getDouble("pressure"); // pressure
		Humidity = Obj_Main.getDouble("humidity");// humidity
		TempMin = Obj_Main.getDouble("temp_min");// min temperature
		TempMax = Obj_Main.getDouble("temp_max");// max temperature

		// get information from wind object
		JSONObject Obj_wind = JsonData.getJSONObject("wind");
		WindSpeed = Obj_wind.getDouble("speed");// wind speed
		WindDir = Obj_wind.getDouble("deg");// wind direction

		// get information from weather object
		JSONArray Array_Weather = JsonData.getJSONArray("weather");
		JSONObject Obj_Wea = Array_Weather.getJSONObject(0);
		Sky = Obj_Wea.getString("description");

		System.out.println(Country + "\n" + City + "\n" + Temp + "\n"
				+ Pressure + "\n" + Humidity + "\n" + TempMin + "\n" + TempMax
				+ "\n" + WindSpeed + "\n" + WindDir + "\n" + Sky + "\n"
				+ Sunrise + "\n" + getSunsetTime());
	}

	// Getters
	public double getWindSpeed() {
		return WindSpeed;
	}

	public double getPressure() {
		return Pressure;
	}

	public double getHumidity() {
		return Humidity;
	}

	public String getSunriseTime() {
		return Sunrise;
	}

	public String getSunsetTime() {
		return Sunset;
	}

	public double getWindDirection() {
		return WindDir;
	}

	public String getCity() {
		return City;
	}

	public double getTemp() {
		return Temp;
	}

	public double getTempMax() {
		return TempMax;
	}

	public String getCountry() {
		return Country;
	}

	public String getSkyCondition() {
		return Sky;
	}

	// Setters
	public void setCity(String City) {
		this.City = City;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	public void setTemp(double Temp) {
		this.Temp = Temp;
	}

	public void setPressure(double Pressure) {
		this.Pressure = Pressure;
	}

	public void setHumidity(double Humidity) {
		this.Humidity = Humidity;
	}

	public void setTempMin(double TempMin) {
		this.TempMin = TempMin;
	}

	public void setTempMax(double TempMax) {
		this.TempMax = TempMax;
	}

	public void setWinddirection(double WindDir) {
		this.WindDir = WindDir;
	}

	public void setWindspeed(double Windspeed) {
		this.WindSpeed = Windspeed;
	}

	public void setSky(String Sky) {
		this.Sky = Sky;
	}

	public void setSunrise(String Sunrise) {
		this.Sunrise = Sunrise;
	}

	public void setSunset(String Sunset) {
		this.Sunset = Sunset;
	}

	public static void main(String[] args) {
		try {
			new CurrentWeather("shanghai,CN"); // using shanghai CN to test
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}