package com.shubham.spring.carrentalservice.serviceInterface;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
@Service
public interface CarService {

	public Car getCarById(String id) throws CarNotFoundException;
	
	public List<Car> getAllCar();
	
	public List<Car> getAllAvailableCar(LocalDate startDate, LocalDate endDate) throws CarNotFoundException;
	
	public List<Car> getAvailableCarByCarTypeAndCarModel(LocalDate startDate, LocalDate endDate, String carType, String carModel) throws CarNotFoundException;
	
	public List<Car> getAvailableCarByCarType(LocalDate startDate, LocalDate endDate, String carType) throws CarNotFoundException;
	
	public List<Car> getAvailableCarByCarModel(LocalDate startDate, LocalDate endDate, String carModel) throws CarNotFoundException;
}
