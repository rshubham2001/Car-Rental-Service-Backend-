package com.shubham.spring.carrentalservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shubham.spring.carrentalservice.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

	@Query(value = "select c from Car c where c.carId not in (select r.car.carId from Rental r "
			+ "where (r.reservationStartDate between ?1 and ?2 or r.reservationEndDate between ?1 and ?2) "
			+ "and r.reservationStatus = 'Confirm')")
	public List<Car> getAllAvailableCar(LocalDate startDate, LocalDate endDate);

	@Query(value = "select c from Car c where c.carId not in (select r.car.carId from Rental r "
			+ "where (r.reservationStartDate between ?1 and ?2 or r.reservationEndDate between ?1 and ?2) "
			+ "and r.reservationStatus = 'Confirm') and c.carType=?3 and c.carModel=?4")
	public List<Car> getAvailableCarByCarTypeAndCarModel(LocalDate startDate, LocalDate endDate, String carType, String carModel);

	@Query(value = "select c from Car c where c.carId not in (select r.car.carId from Rental r "
			+ "where (r.reservationStartDate between ?1 and ?2 or r.reservationEndDate between ?1 and ?2) "
			+ "and r.reservationStatus = 'Confirm') and c.carType=?3")
	public List<Car> getAvailableCarByCarType(LocalDate startDate, LocalDate endDate, String carType);
	
	@Query(value = "select c from Car c where c.carId not in (select r.car.carId from Rental r "
			+ "where (r.reservationStartDate between ?1 and ?2 or r.reservationEndDate between ?1 and ?2) "
			+ "and r.reservationStatus = 'Confirm') and c.carModel=?3")
	public List<Car> getAvailableCarByCarModel(LocalDate startDate, LocalDate endDate,String carModel);

}
