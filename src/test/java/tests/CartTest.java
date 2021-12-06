package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class CartTest {

    @Test
    public void carComparisonTest() {
        Car car1 = new Car();
        car1.setMake("1");
        car1.setModel("2");
        car1.setSpeed(222);
        Car car2 = new Car();
        car2.setMake("1");
        car2.setModel("2");
        car2.setSpeed(222);

        Car car3 = new Car("2", "3", 4);
        Assert.assertEquals(car1.getModel(), car3.getModel());
    }

    @Test
    public void carComparison2Test() {
        Car car1 = Car.builder()
                .make("1")
                .model("2")
                .build();
        Car car2 = Car.builder()
                .make("1")
                .model("3")
                .build();
        Assert.assertEquals(car1.getModel(), car2.getModel());
    }

    @Test
    public void loggerTest() {
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
