public class MoistureController {
    private Sprinkler sprinkler;
    private WeatherMoist weather;

    public MoistureController(Sprinkler s, WeatherMoist w) {
        this.sprinkler = s;
        this.weather = w;
        sprinkler.start();
        weather.start();
    }
    public void runSprinkler() {
        sprinkler.stop = false;
    }
    public void idleSprinkler() {
        sprinkler.stop = true;
    }

    public void runWeather() {
        weather.stop = false;
    }
    public void idleWeather() {
        weather.stop = true;
    }
}
