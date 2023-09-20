public class Humidifier extends Thread {
    private Greenhouse greenhouse;
    private double rate;
    public volatile boolean stop = true;

    public Humidifier(Greenhouse h, double r, double w) {
        this.greenhouse = h;
        this.rate = r + w;
    }
    public void run() {
        while (true) {
            while (!stop) {
                greenhouse.changeHumidity(rate / 60);
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
    public synchronized void setStop(boolean b) {
        this.stop = b;
    }
    public synchronized boolean getStatus() {
        return this.stop;
    }
    public String getStringStatus() {
        String x = "";
        if (this.stop == true)
            x = "Off";
        if (this.stop == false)
            x = "On";
        return x;
    }
}
