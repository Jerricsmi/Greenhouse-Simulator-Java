public class Greenhouse {
    private static double temperature;
    private static double soilMoisture;
    private static double humidity;

    public Greenhouse(double startingTemp, double startingMoist) {
        this.temperature = startingTemp;
        this.soilMoisture = startingMoist;

    }
    public Greenhouse() {
        this.temperature = 0.0;
        this.soilMoisture = 0.0;
    }
    public void setTemperature(double inputTemp) {
        this.temperature = inputTemp;
    }
    public double getTemperature() {
        return this.temperature;
    }
    public synchronized void changeTemperature(double deltaTemp) {
        this.temperature += deltaTemp;
    }
    public void setSoilMoisture(double inputMoist) {
        this.soilMoisture = inputMoist;
    }
    public double getSoilMoisture() {
        return this.soilMoisture;
    }
    public synchronized void changeMoisture(double deltaMoist) {
        this.soilMoisture += deltaMoist;
    }
    public void setHumidity(double inputHum) {
        this.humidity = inputHum;
    }
    public double getHumidity() {
        return this.humidity;
    }
    public synchronized void changeHumidity(double deltaHum){
        this.humidity += deltaHum;
    }
}

