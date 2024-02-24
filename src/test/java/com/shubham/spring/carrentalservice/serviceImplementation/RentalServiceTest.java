package com.shubham.spring.carrentalservice.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.CarNotFoundException;
import com.shubham.spring.carrentalservice.exception.CustomerException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.exception.WrongDateException;
import com.shubham.spring.carrentalservice.model.RentalInputModel;
import com.shubham.spring.carrentalservice.model.RentalOutputModel;
import com.shubham.spring.carrentalservice.repository.RentalRepository;
import com.shubham.spring.carrentalservice.serviceInterface.RentalService;
import com.shubham.spring.carrentalservice.util.MapRental;
@SpringBootTest
public class RentalServiceTest {
	@Mock
	private CustomerServiceImplementation customerService;

	@Mock
	private CarServiceImplementation carService;

	@Mock
	private RentalRepository rentalRepository;
	

	@InjectMocks
	private RentalService rentalService = new RentalServiceImplementation();


	Car car = new Car();
	Customer  customer = new Customer();
	String customerId = "abc12@gmail.com";
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		car.setCapacity(4);
		car.setCarId("C001");
		car.setCarMake("Tata");
		car.setCarModel("Thar");
		car.setCarRentalRate(100.0);
		car.setCarType("SUV");
		
		customer.setAddress("Kolkata");
		customer.setCustomerName("Shubham");
		customer.setEmailId(customerId);
		customer.setDateOfBirth(LocalDate.of(2000, 06, 06));
		customer.setDrivingLicence("WB13 20229876123");
		customer.setPassword("password123");
		customer.setPhone("9876543210");
	}

	@Test
	public void testGetById_ExistingRentalId_ReturnsRental() throws InvalidRentalIdException {
		// Arrange
		int rentalId = 1;
		Rental rental = new Rental();
		when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));

		// Act
		Rental result = rentalService.getById(rentalId);

		// Assert
		assertEquals(rental, result);
	}

	@Test
	public void testGetById_NonExistingRentalId_ThrowsInvalidRentalIdException() {
		// Arrange
		int rentalId = 1;
		when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(InvalidRentalIdException.class, () -> rentalService.getById(rentalId));
	}

	@Test
	public void testGetRentalById_ExistingRentalId_ReturnsRentalOutputModel() throws InvalidRentalIdException {
		// Arrange
		int rentalId = 1;
		
		
		Rental rental = new Rental();
		rental.setCustomer(customer);
		rental.setCar(car);
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.of(2023, 07, 11);
		rental.setReservationStartDate(startDate);
		rental.setReservationEndDate(endDate);
		when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));

		// Act
		RentalOutputModel result = rentalService.getRentalById(rentalId);

		// Assert
		assertNotNull(Optional.of(result));
		// Add additional assertions as per the expected behavior
	}

	@Test
	public void testGetRentalById_NonExistingRentalId_ThrowsInvalidRentalIdException() {
		// Arrange
		int rentalId = 1;
		when(rentalRepository.findById(rentalId)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(InvalidRentalIdException.class, () -> rentalService.getRentalById(rentalId));
	}

	@Test
	public void testGetRentalsByCustomerId_ExistingCustomerId() throws InvalidRentalIdException {
		// Arrange       
		String customerId = "abc12@gmail.com";
		
		
		
		
		Rental rental1 = new Rental();
		Rental rental2 = new Rental();
		
		rental1.setCustomer(customer);
		rental2.setCustomer(customer);
		rental1.setCar(car);
		rental2.setCar(car);
		
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.of(2023, 07, 11);
		rental1.setReservationStartDate(startDate);
		rental1.setReservationEndDate(endDate);
		
		rental2.setReservationStartDate(startDate);
		rental2.setReservationEndDate(endDate);
		
		List<Rental> rentalList = Arrays.asList(rental1, rental2);
		when(rentalRepository.getRentalsByCustomerId(customerId)).thenReturn(rentalList);

	    // Act       
	    List<RentalOutputModel> result = rentalService.getRentalsByCustomerId(customerId);

	    // Assert       
	    assertNotNull(Optional.of(result));
        assertEquals(rentalList.size(), result.size()); 
	}

	@Test
	public void testGetRentalsByCustomerId_NonExistingCustomerId_ThrowsInvalidRentalIdException() {
		// Arrange
		String customerId = "123";
		when(rentalRepository.getRentalsByCustomerId(customerId)).thenReturn(Collections.emptyList());

		// Act and Assert
		assertThrows(InvalidRentalIdException.class, () -> rentalService.getRentalsByCustomerId(customerId));
	}

	@Test 
	public void testRentCar_ValidInputModel_ReturnsRentalOutputModel() throws CustomerException, CarNotFoundException, WrongDateException { 
		// Arrange        
		RentalInputModel rentalModel = new RentalInputModel(); 
		rentalModel.setEmailId("customer@example.com"); 
		rentalModel.setCarId("C001");
		rentalModel.setReservationStartDate(LocalDate.now().plusDays(1));
		rentalModel.setReservationEndDate(LocalDate.now().plusDays(5));
		 
		Customer customer = new Customer(); 
		customer.setEmailId("customer@example.com");
		
		when(customerService.getCustomerByEmailId(rentalModel.getEmailId())).thenReturn(customer);
		
		Car car = new Car(); 
		car.setCarRentalRate(100.0); 
		
		List<Car> availableCars = new ArrayList<>();
        availableCars.add(car);
        
        when(carService.getAllAvailableCar(LocalDate.now().plusDays(1), LocalDate.now().plusDays(5))).thenReturn(availableCars);
        when(carService.getCarById(rentalModel.getCarId())).thenReturn(car);
        
		Rental rental = new Rental();
	
		when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
		
		// Act        
		RentalOutputModel result = rentalService.rentCar(rentalModel);
		 
		// Assert        
		assertNotNull(result);
		assertEquals(customer.getEmailId(), result.getCustomerId());
        assertEquals(car.getCarId(), result.getCarId());
		}

	@Test
	public void testRentCar_InvalidReservationStartDate_ThrowsWrongDateException()
			throws CustomerException, CarNotFoundException, WrongDateException {
		// Arrange
		RentalInputModel rentalModel = new RentalInputModel();
		rentalModel.setEmailId("customer@example.com");
		rentalModel.setCarId("1");
		rentalModel.setReservationStartDate(LocalDate.now().minusDays(1));
		rentalModel.setReservationEndDate(LocalDate.now().plusDays(5));

		// Act and Assert
		assertThrows(WrongDateException.class, () -> rentalService.rentCar(rentalModel));
	}

	@Test
	public void testRentCar_InvalidReservationEndDate_ThrowsWrongDateException()
			throws CustomerException, CarNotFoundException, WrongDateException {
		// Arrange
		RentalInputModel rentalModel = new RentalInputModel();
		rentalModel.setEmailId("customer@example.com");
		rentalModel.setCarId("1");
		rentalModel.setReservationStartDate(LocalDate.now().plusDays(1));
		rentalModel.setReservationEndDate(LocalDate.now().minusDays(5));

		// Act and Assert
		assertThrows(WrongDateException.class, () -> rentalService.rentCar(rentalModel));
	}

	@Test
	public void testRentCar_MissingCarRentalRate_ThrowsCarNotFoundException()
			throws CustomerException, CarNotFoundException, WrongDateException {
		// Arrange
		RentalInputModel rentalModel = new RentalInputModel();
		rentalModel.setEmailId("customer@example.com");
		rentalModel.setCarId("1");
		rentalModel.setReservationStartDate(LocalDate.now().plusDays(1));
		rentalModel.setReservationEndDate(LocalDate.now().plusDays(5));

		Customer customer = new Customer();
		Car car = new Car();
		car.setCarRentalRate(null);

		when(customerService.getCustomerByEmailId(rentalModel.getEmailId())).thenReturn(customer);
		when(carService.getCarById(rentalModel.getCarId())).thenReturn(car);

		// Act and Assert
		assertThrows(CarNotFoundException.class, () -> rentalService.rentCar(rentalModel));
	}
	
	@Test
	public void testRentCarSameDayReservationStartAndReservationEndDay() {
		RentalInputModel rentalModel = new RentalInputModel();
		rentalModel.setEmailId("customer@example.com");
		rentalModel.setCarId("1");
		rentalModel.setReservationStartDate(LocalDate.now());
		rentalModel.setReservationEndDate(LocalDate.now());
		
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setRentalId(1);
		rental.setReservationStartDate(rentalModel.getReservationStartDate());
		rental.setReservationEndDate(rentalModel.getReservationEndDate());
		
		RentalOutputModel rentalOutputModel = MapRental.mapToRentalOutput(rental);
		assertEquals(1,rentalOutputModel.getResrvationDuration());
		
	}
}
