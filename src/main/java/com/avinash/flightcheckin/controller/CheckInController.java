package com.avinash.flightcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avinash.flightcheckin.integration.ReservationRestClient;
import com.avinash.flightcheckin.integration.dto.Reservation;
import com.avinash.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClient reservationRestClient;

	@RequestMapping("/showStartCheckin")
	public String showStartCheckIn() {
		return "startCheckIn";
	}

	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
		Reservation reservation = reservationRestClient.findReservation(reservationId);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}

	@RequestMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
			@RequestParam("numberOfBags") int numberOfBags) {

		ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();

		reservationUpdateRequest.setCheckedIn(true);
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		reservationRestClient.updateReservation(reservationUpdateRequest);

		return "checkInConfirmation";
	}

}
