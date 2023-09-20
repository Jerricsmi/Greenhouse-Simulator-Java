public class HumiditySensor extends Thread {
    private Greenhouse greenhouse;
    private HumidityController humController;
    private volatile double desiredHumFrom;
    private volatile double desiredHumTo;
    public volatile boolean stop = true;

    public HumiditySensor(Greenhouse h, HumidityController c) {
        this.greenhouse = h;
        this.humController = c;
        this.desiredHumFrom = 0.0;
        this.desiredHumTo = 0.0;
    }

    public void run() {
        while(true) {
            while (!stop) {
                humController.runWeather();
                if (greenhouse.getHumidity() < desiredHumFrom && !stop) {
                    humController.idleWeather();
                    humController.runHumidifier();
                    while ((greenhouse.getHumidity() < (desiredHumFrom + desiredHumTo) / 2) && !stop);
                    humController.idleHumidifier();
                    humController.runWeather();
                }
            }
            humController.idleWeather();
            while (stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public synchronized void setDesiredHumFrom(double inputTemp) {
        this.desiredHumFrom = inputTemp;
    }
    public synchronized void setDesiredHumTo(double inputTemp) {
        this.desiredHumTo = inputTemp;
    }
}
