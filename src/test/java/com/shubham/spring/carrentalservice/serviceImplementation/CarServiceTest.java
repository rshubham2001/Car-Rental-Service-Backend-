package com.shubham.spring.carrentalservice.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.repository.CarRepository;

@SpringBootTest
public class CarServiceTest {
	@Mock
	private CarRepository carRepository;

	@InjectMocks
	private CarServiceImplementation carService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetCarById() throws CarNotFoundException {
		// Mock data
		String carId = "ABC123";
		Car car = new Car();
		car.setCarId(carId);

		// Mock dependencies
		when(carRepository.findById(carId)).thenReturn(Optional.of(car));

		// Invoke the method
		Car result = carService.getCarById(carId);

		// Verify interactions and assertions
		verify(carRepository).findById(carId);
		assertEquals(carId, result.getCarId());
	}

	@Test
	void testGetCarById_CarNotFound() {
		// Mock data
		String carId = "XYZ789";

		// Mock dependencies
		when(carRepository.findById(carId)).thenReturn(Optional.empty());

		// Invoke the method and assert exception
		assertThrows(CarNotFoundException.class, () -> {
			carService.getCarById(carId);
		});

		// Verify interactions
		verify(carRepository).findById(carId);
	}

	@Test
	void testGetAllCar() {
		List<Car> carList = new ArrayList<>();
		carList.add(new Car());
		carList.add(new Car());

		when(carRepository.findAll()).thenReturn(carList);

		List<Car> result = carService.getAllCar();

		verify(carRepository).findAll();
		assertEquals(2, result.size());
	}

	@Test
	void testGetAllAvailableCar() throws CarNotFoundException {
		// Mock data       
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		List<Car> carList = new ArrayList<>();
		carList.add(new Car());
		carList.add(new Car());

		// Mock dependencies       
		when(carRepository.getAllAvailableCar(startDate, endDate)).thenReturn(carList);
		// Invoke the method       
		List<Car> result = carService.getAllAvailableCar(startDate, endDate);
		// Verify interactions and assertions       
		verify(carRepository).getAllAvailableCar(startDate, endDate);
		assertEquals(2, result.size());
	}

	@Test
	void testGetAllAvailableCar_NoCarAvailable() {
		// Mock data
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);

		// Mock dependencies
		when(carRepository.getAllAvailableCar(startDate, endDate)).thenReturn(new ArrayList<>());

		// Invoke the method and assert exception
		assertThrows(CarNotFoundException.class, () -> {
			carService.getAllAvailableCar(startDate, endDate);
		});

		// Verify interactions
		verify(carRepository).getAllAvailableCar(startDate, endDate);
	}

	@Test
	void testGetAvailableCarByCarTypeAndCarModel() throws CarNotFoundException {
		// Mock data       
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		String carType = "Sedan";
		String carModel = "Toyota";

		List<Car> carList = new ArrayList<>();
		carList.add(new Car());
		carList.add(new Car());
		// Mock dependencies       
		when(carRepository.getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel))
				.thenReturn(carList);
		// Invoke the method       
		List<Car> result = carService.getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel);
		// Verify interactions and assertions       
		verify(carRepository).getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel);
		assertEquals(2, result.size());
	}

	@Test
	void testGetAvailableCarByCarTypeAndCarModel_NoCarAvailable() {
		// Mock data       
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		String carType = "Sedan";
		String carModel = "Toyota";

		// Mock dependencies       
		when(carRepository.getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel))
				.thenReturn(new ArrayList<>());
		// Invoke the method and assert exception
		assertThrows(CarNotFoundException.class, () -> {
			carService.getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel);
		});
		// Verify interactions       
		verify(carRepository).getAvailableCarByCarTypeAndCarModel(startDate, endDate, carType, carModel);
	}

	@Test
	void testGetAvailableCarByCarType() throws CarNotFoundException {
		// Mock data       
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		String carType = "Sedan";

		List<Car> carList = new ArrayList<>();
		carList.add(new Car());
		carList.add(new Car());

		// Mock dependencies       
		when(carRepository.getAvailableCarByCarType(startDate, endDate, carType)).thenReturn(carList);
		// Invoke the method       
		List<Car> result = carService.getAvailableCarByCarType(startDate, endDate, carType);
		// Verify interactions and assertions       
		verify(carRepository).getAvailableCarByCarType(startDate, endDate, carType);
		assertEquals(2, result.size());
	}

	@Test
	void testGetAvailableCarByCarType_NoCarAvailable() {
		// Mock data       
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		String carType = "Sedan";

		// Mock dependencies       
		when(carRepository.getAvailableCarByCarType(startDate, endDate, carType)).thenReturn(new ArrayList<>());
		// Invoke the method and assert exception       
		assertThrows(CarNotFoundException.class, () -> {
			carService.getAvailableCarByCarType(startDate, endDate, carType);
		});
		// Verify interactions       
		verify(carRepository).getAvailableCarByCarType(startDate, endDate, carType);
	}

	@Test
	void testGetAvailableCarByCarModel() throws CarNotFoundException {
		// Mock data       
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		String carModel = "Toyota";

		List<Car> carList = new ArrayList<>();
		carList.add(new Car());
		carList.add(new Car());
		// Mock dependencies       
		when(carRepository.getAvailableCarByCarModel(startDate, endDate, carModel)).thenReturn(carList);
		// Invoke the method       
		List<Car> result = carService.getAvailableCarByCarModel(startDate, endDate, carModel);
		// Verify interactions and assertions       
		verify(carRepository).getAvailableCarByCarModel(startDate, endDate, carModel);
		assertEquals(2, result.size());
	}

	@Test
	void testGetAvailableCarByCarModel_NoCarAvailable() { 
		// Mock data        
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(7);
		String carModel = "Toyota";
		// Mock dependencies        
		when(carRepository.getAvailableCarByCarModel(startDate, endDate, carModel)).thenReturn(new ArrayList<>());
		// Invoke the method and assert exception        
		assertThrows(CarNotFoundException.class, () -> {carService.getAvailableCarByCarModel(startDate, endDate, carModel);});
		 // Verify interactions        
		verify(carRepository).getAvailableCarByCarModel(startDate, endDate, carModel);
		}
}
