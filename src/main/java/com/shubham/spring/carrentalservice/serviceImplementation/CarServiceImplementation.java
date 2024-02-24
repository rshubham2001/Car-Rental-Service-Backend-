package com.shubham.spring.carrentalservice.serviceImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.repository.CarRepository;
import com.shubham.spring.carrentalservice.serviceInterface.CarService;

@Service
public class CarServiceImplementation implements CarService{

	@Autowired
	CarRepository carRepository;
	
	
	
	public Car getCarById(String id) throws CarNotFoundException {
		Car car = carRepository.findById(id).orElse(null);
		if(car!=null) {
			return car;
		}
		throw new CarNotFoundException("Car Not Found");
	}
	
	public List<Car> getAllCar(){
		return carRepository.findAll();
	}
	
	public List<Car> getAllAvailableCar(LocalDate startDate, LocalDate endDate) throws CarNotFoundException{
		List<Car> carList = carRepository.getAllAvailableCar(startDate, endDate);
		if(!carList.isEmpty()) {
			return carList;
		}
		throw new CarNotFoundException("No Car Available");
	}
	
	public List<Car> getAvailableCarByCarTypeAndCarModel(LocalDate startDate, LocalDate endDate, String carType, String carModel) throws CarNotFoundException{
		List<Car> carList = carRepository.getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel);
		if(!carList.isEmpty()) {
			return carList;
		}
		throw new CarNotFoundException("Car of this type with this model or for this date is not Available");
	}
	
	public List<Car> getAvailableCarByCarType(LocalDate startDate, LocalDate endDate, String carType) throws CarNotFoundException{
		List<Car> carList = carRepository.getAvailableCarByCarType(startDate, endDate, carType);
		if(!carList.isEmpty()) {
			return carList;
		}
		throw new CarNotFoundException("Car of this type or for this date is not Available");
	}
	
	public List<Car> getAvailableCarByCarModel(LocalDate startDate, LocalDate endDate, String carModel) throws CarNotFoundException{
		List<Car> carList = carRepository.getAvailableCarByCarModel(startDate, endDate, carModel);
		if(!carList.isEmpty()) {
			return carList;
		}
		throw new CarNotFoundException("Car of this model or for this date is not Available");
	}
	
	
}
