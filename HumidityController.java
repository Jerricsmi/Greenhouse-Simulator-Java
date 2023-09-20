public class HumidityController {
    private Humidifier humidifier;
    private WeatherHum weather;

    public HumidityController(Humidifier h, WeatherHum w) {
        this.humidifier = h;
        this.weather = w;
        humidifier.start();
        weather.start();
    }
    public void runHumidifier() {
        humidifier.stop = false;
    }
    public void idleHumidifier() {
        humidifier.stop = true;
    }

    public void runWeather() {
        weather.stop = false;
    }
    public void idleWeather() {
        weather.stop = true;
    }
}
