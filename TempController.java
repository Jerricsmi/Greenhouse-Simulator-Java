public class TempController extends Thread {
    private Furnace furnace;
    private AirConditioner ac;
    private WeatherTemp weather;

    public TempController(Furnace f, AirConditioner a, WeatherTemp w) {
        this.furnace = f;
        this.ac = a;
        this.weather = w;
        furnace.start();
        ac.start();
        weather.start();
    }

    public void runFurnace() {
        furnace.stop = false;
    }

    public void idleFurnace() {
        furnace.stop = true;
    }

    public void runAirConditioner() {
        ac.stop = false;
    }

    public void idleAirConditioner() {
        ac.stop = true;
    }

    public void runWeather() {
        weather.stop = false;
    }

    public void idleWeather() {
        weather.stop = true;
    }
}
