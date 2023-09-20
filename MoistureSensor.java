public class MoistureSensor extends Thread {
    private Greenhouse greenhouse;
    private MoistureController moistController;
    private volatile double desiredMoistFrom;
    private volatile double desiredMoistTo;
    public volatile boolean stop = true;

    public MoistureSensor(Greenhouse h, MoistureController c) {
        this.greenhouse = h;
        this.moistController = c;
        this.desiredMoistFrom = 0.0;
        this.desiredMoistTo = 0.0;
    }

    public void run() {
        while(true) {
            while (!stop) {
                moistController.runWeather();
                if (greenhouse.getSoilMoisture() < desiredMoistFrom && !stop) {
                    moistController.idleWeather();
                    moistController.runSprinkler();
                    while (greenhouse.getSoilMoisture() < ((desiredMoistTo + desiredMoistFrom) / 2) && !stop);
                    moistController.idleSprinkler();
                    moistController.runWeather();
                }
            }
            moistController.idleWeather();
            while (stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public synchronized void setDesiredMoistFrom(double inputTemp) {
        this.desiredMoistFrom = inputTemp;
    }
    public synchronized void setDesiredMoistTo(double inputTemp) {
        this.desiredMoistTo = inputTemp;
    }
}
