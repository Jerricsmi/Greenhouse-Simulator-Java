public class Sprinkler extends Thread {
    private Greenhouse greenhouse;
    private double rate;
    public volatile boolean stop = true;
    private double weather;

    public Sprinkler(Greenhouse h, double r, double w) {
        this.greenhouse = h;
        this.weather = w;
        this.rate = r + w;
    }
    public void run() {
        while (true) {
            while (!stop) {
                greenhouse.changeMoisture(rate / 60);
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
//    public void setStatus(boolean b) {
//        this.status = b;
//    }
//    public boolean getStatus() {
//        return this.status;
//    }
    public String getStringStatus() {
        String x = "";
        if (this.stop == true)
            x = "Off";
        if (this.stop == false)
            x = "On";
        return x;
    }
}
