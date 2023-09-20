public class WeatherTemp extends Thread {
    private Greenhouse greenhouse;
    private double rate;
    public volatile boolean stop = true;

    public WeatherTemp(Greenhouse h, double r) {
        this.greenhouse = h;
        this.rate = r;
    }
    public void run() {
        while (true) {
            while (!stop) {
                greenhouse.changeTemperature(rate / 60);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            while (stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
