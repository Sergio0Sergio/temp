package web.controller;

import domain.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String printWelcome(@RequestParam(defaultValue = "5") int count, ModelMap model) {
        List<Car> cars = new ArrayList<>();
        List<Car> carsAll = new ArrayList<>();
        carsAll.add(new Car("A", "blue", 11));
        carsAll.add(new Car("B", "red", 21));
        carsAll.add(new Car("C", "green", 31));
        carsAll.add(new Car("D", "orange", 41));
        carsAll.add(new Car("E", "violet", 51));

        if (count > carsAll.size()){
            count = carsAll.size();
        }

        for (int i = 0; i < count; i++) {
            cars.add(carsAll.get(i));
        }

        model.addAttribute("cars", cars);
        return "cars";
    }
}
