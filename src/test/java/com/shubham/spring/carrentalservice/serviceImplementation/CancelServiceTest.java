package com.shubham.spring.carrentalservice.serviceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.shubham.spring.carrentalservice.entity.CancelledRental;
import com.shubham.spring.carrentalservice.entity.Car;
import com.shubham.spring.carrentalservice.entity.Customer;
import com.shubham.spring.carrentalservice.entity.Rental;
import com.shubham.spring.carrentalservice.exception.InvalidCancellationException;
import com.shubham.spring.carrentalservice.exception.InvalidRentalIdException;
import com.shubham.spring.carrentalservice.model.CancelRentalOutputModel;
import com.shubham.spring.carrentalservice.repository.CancelRepository;
import com.shubham.spring.carrentalservice.repository.RentalRepository;

@SpringBootTest
public class CancelServiceTest {

	@Mock
	private CancelRepository cancelRepository;

	@Mock
	private RentalRepository rentalRepository;

	@Mock
	private RentalServiceImplementation rentalServiceImplementation;

	@InjectMocks
	private CancelServiceImplementation cancelServiceImplementation;

	Car car = new Car();

	Customer customer = new Customer();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		car.setCarId("C001");
		car.setCapacity(4);
		car.setCarMake("Tata");
		car.setCarModel("Thar");
		car.setCarRentalRate(100.0);
		car.setCarType("SUV");

		String customerId = "abc12@gmail.com";

		customer.setAddress("Kolkata");
		customer.setCustomerName("Shubham");
		customer.setEmailId(customerId);
		customer.setDateOfBirth(LocalDate.of(2000, 06, 06));
		customer.setDrivingLicence("WB13 20229876123");
		customer.setPassword("password123");
		customer.setPhone("9876543210");
	}

	@Test
	void testCancelRentals_ReservationAlreadyCancelled() throws InvalidRentalIdException {
		// Arrange
		Integer rentalId = 1;
		Rental rental = new Rental();
		rental.setTotalCost(100.0);
		rental.setReservationStatus("Cancelled");
		when(rentalServiceImplementation.getById(rentalId)).thenReturn(rental);

		// Act and Assert
		assertThrows(InvalidCancellationException.class, () -> cancelServiceImplementation.cancelRentals(rentalId));
		
		verify(rentalServiceImplementation, times(1)).getById(rentalId);
		
		verify(cancelRepository, never()).save(any());
		
		verify(rentalRepository, never()).save(any());
	}
	
    @Test
    void testCancelRentals_ValidCancellationBeforeTwoDays() throws InvalidCancellationException, InvalidRentalIdException {
        // Arrange
        Integer rentalId = 1;
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setReservationStatus("Active");
        rental.setTotalCost(100.0);
        
        CancelledRental cancelledRental = new CancelledRental();
        
        rental.setReservationStartDate(LocalDate.now().plusDays(3)); // Reservation start date is 3 days from today
        when(rentalServiceImplementation.getById(rentalId)).thenReturn(rental);
        when(cancelRepository.save(any(CancelledRental.class))).thenReturn(cancelledRental);

        // Act
        CancelRentalOutputModel outputModel = cancelServiceImplementation.cancelRentals(rentalId);

        // Assert
        Assertions.assertNotNull(outputModel);
        verify(cancelRepository, times(1)).save(any());
        verify(rentalRepository, times(1)).save(any());
    }
    
    @Test
    void testCancelRentals_ValidCancellationAfterTwoDays() throws InvalidCancellationException, InvalidRentalIdException {
        // Arrange
        Integer rentalId = 1;
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setReservationStatus("Active");
        rental.setTotalCost(100.0);
        CancelledRental cancelledRental = new CancelledRental();
        rental.setReservationStartDate(LocalDate.now().plusDays(1)); // Reservation start date is 1 days from today
        when(rentalServiceImplementation.getById(rentalId)).thenReturn(rental);
        when(cancelRepository.save(any(CancelledRental.class))).thenReturn(cancelledRental);

        // Act
        CancelRentalOutputModel outputModel = cancelServiceImplementation.cancelRentals(rentalId);

        // Assert
        Assertions.assertNotNull(outputModel);
        verify(cancelRepository, times(1)).save(any());
        verify(rentalRepository, times(1)).save(any());
    }
    
    @Test
    void testCancelRentals_ValidCancellationBeforeSevenDays() throws InvalidCancellationException, InvalidRentalIdException {
        // Arrange
        Integer rentalId = 1;
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setReservationStatus("Active");
        rental.setTotalCost(100.0);
        
        CancelledRental cancelledRental = new CancelledRental();
        
        rental.setReservationStartDate(LocalDate.now().plusDays(7)); // Reservation start date is 7 days from today
        when(rentalServiceImplementation.getById(rentalId)).thenReturn(rental);
        when(cancelRepository.save(any(CancelledRental.class))).thenReturn(cancelledRental);

        // Act
        CancelRentalOutputModel outputModel = cancelServiceImplementation.cancelRentals(rentalId);

        // Assert
        Assertions.assertNotNull(outputModel);
        verify(cancelRepository, times(1)).save(any());
        verify(rentalRepository, times(1)).save(any());
    }

    @Test
    void testGetCancelledRentalsByEmailId_NoCancelledRentals() {
        // Arrange
        String emailId = "test@example.com";
        when(cancelRepository.getCancelledRentalsByEmailId(emailId)).thenReturn(new ArrayList<>());

        // Act and Assert
        Assertions.assertThrows(InvalidCancellationException.class, () -> {
        	cancelServiceImplementation.getCancelledRentalsByEmailId(emailId);
        });

        
        verify(cancelRepository, times(1)).getCancelledRentalsByEmailId(emailId);
    }

    @Test
    void testGetCancelledRentalsByEmailId_ValidEmailId() throws InvalidCancellationException {
        // Arrange
        String emailId = "abc12@gmail.com";
        Rental rental = new Rental();
        rental.setRentalId(1);
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setReservationStatus("cancelled");
        
        CancelledRental cancelledRental = new CancelledRental();
        cancelledRental.setCancelledId(1);
        cancelledRental.setCustomer(customer);
        cancelledRental.setRefundAmount(0);
        cancelledRental.setRental(rental);
        cancelledRental.setCancelledDate(LocalDate.now());
        List<CancelledRental> cancelledRentalList = new ArrayList<>();
        cancelledRentalList.add(cancelledRental);
        when(cancelRepository.getCancelledRentalsByEmailId(emailId)).thenReturn(cancelledRentalList);

        // Act
        List<CancelRentalOutputModel> outputModels = cancelServiceImplementation.getCancelledRentalsByEmailId(emailId);

        // Assert
       assertEquals(cancelledRentalList.size(), outputModels.size());
        verify(cancelRepository, times(1)).getCancelledRentalsByEmailId(emailId);
    }
}
