package domain;

public class Car {

    private String model;
    private String color;
    private int series;

    public Car(){}

    public Car(String model, String color, int series){
        this.model = model;
        this.color = color;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getSeries() != car.getSeries()) return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null) return false;
        return getColor() != null ? getColor().equals(car.getColor()) : car.getColor() == null;
    }

    @Override
    public int hashCode() {
        int result = getModel() != null ? getModel().hashCode() : 0;
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + getSeries();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", series=" + series +
                '}';
    }
}
