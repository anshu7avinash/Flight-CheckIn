package com.avinash.flightcheckin.integration;

import com.avinash.flightcheckin.integration.dto.Reservation;
import com.avinash.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);

}
