package tests;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    String make;
    String model;
    int speed;


//    public Car(String make, String model, int speed) {
//        this.make = make;
//        this.model = model;
//        this.speed = speed;
//    }
//
//    public Car() {
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Car car = (Car) o;
//        return Objects.equals(make, car.make) &&
//                Objects.equals(model, car.model);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(make, model);
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "make='" + make + '\'' +
//                ", model='" + model + '\'' +
//                '}';
//    }
}
