import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
public class TempSensor extends Thread {
    private Greenhouse greenhouse;
    private TempController tempcontroller;
    private volatile double desiredTemp;
    public volatile boolean stop = true;

    public TempSensor(Greenhouse h, TempController c) {
        this.greenhouse = h;
        this.tempcontroller = c;
        this.desiredTemp = 0.0;
    }

    public void run() {
        while (true) {
            while (!stop) {
                tempcontroller.runWeather();
                if (greenhouse.getTemperature() < desiredTemp - 3 && !stop) {
                    tempcontroller.idleWeather();
                    tempcontroller.runFurnace();
                    while (greenhouse.getTemperature() <= desiredTemp && !stop);
                    tempcontroller.idleFurnace();
                    tempcontroller.runWeather();
                }
                else if (greenhouse.getTemperature() > desiredTemp + 3 && !stop) {
                    tempcontroller.idleWeather();
                    tempcontroller.runAirConditioner();
                    while (greenhouse.getTemperature() >= desiredTemp && !stop);
                    tempcontroller.idleAirConditioner();
                    tempcontroller.runWeather();
                }
        }
            tempcontroller.idleWeather();
            while (stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public synchronized void setDesiredTemp(double inputTemp) {
        this.desiredTemp = inputTemp;
    }
}
