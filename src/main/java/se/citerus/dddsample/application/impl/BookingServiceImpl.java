package se.citerus.dddsample.application.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import se.citerus.dddsample.application.BookingService;
import se.citerus.dddsample.domain.model.cargo.entity.Cargo;
import se.citerus.dddsample.domain.model.cargo.repository.CargoRepository;
import se.citerus.dddsample.domain.model.cargo.vo.Itinerary;
import se.citerus.dddsample.domain.model.cargo.vo.RouteSpecification;
import se.citerus.dddsample.domain.model.cargo.vo.TrackingId;
import se.citerus.dddsample.domain.model.location.entity.Location;
import se.citerus.dddsample.domain.model.location.repository.LocationRepository;
import se.citerus.dddsample.domain.model.location.vo.UnLocode;
import se.citerus.dddsample.domain.service.RoutingService;

public class BookingServiceImpl implements BookingService {

	private final CargoRepository cargoRepository;
	private final LocationRepository locationRepository;
	private final RoutingService routingService;
	private final Log logger = LogFactory.getLog(getClass());

	public BookingServiceImpl(final CargoRepository cargoRepository, final LocationRepository locationRepository,
			final RoutingService routingService) {
		this.cargoRepository = cargoRepository;
		this.locationRepository = locationRepository;
		this.routingService = routingService;
	}

	@Override
	@Transactional
	public TrackingId bookNewCargo(final UnLocode originUnLocode, final UnLocode destinationUnLocode,
			final Date arrivalDeadline) {
		// TODO modeling this as a cargo factory might be suitable
		final TrackingId trackingId = cargoRepository.nextTrackingId();
		final Location origin = locationRepository.find(originUnLocode);
		final Location destination = locationRepository.find(destinationUnLocode);
		final RouteSpecification routeSpecification = new RouteSpecification(origin, destination, arrivalDeadline);

		final Cargo cargo = new Cargo(trackingId, routeSpecification);

		cargoRepository.store(cargo);
		logger.info("Booked new cargo with tracking id " + cargo.trackingId().idString());

		return cargo.trackingId();
	}

	@Override
	@Transactional
	public List<Itinerary> requestPossibleRoutesForCargo(final TrackingId trackingId) {
		final Cargo cargo = cargoRepository.find(trackingId);

		if (cargo == null) {
			return Collections.emptyList();
		}

		return routingService.fetchRoutesForSpecification(cargo.routeSpecification());
	}

	@Override
	@Transactional
	public void assignCargoToRoute(final Itinerary itinerary, final TrackingId trackingId) {
		final Cargo cargo = cargoRepository.find(trackingId);
		if (cargo == null) {
			throw new IllegalArgumentException("Can't assign itinerary to non-existing cargo " + trackingId);
		}

		cargo.assignToRoute(itinerary);
		cargoRepository.store(cargo);

		logger.info("Assigned cargo " + trackingId + " to new route");
	}

	@Override
	@Transactional
	public void changeDestination(final TrackingId trackingId, final UnLocode unLocode) {
		final Cargo cargo = cargoRepository.find(trackingId);
		final Location newDestination = locationRepository.find(unLocode);

		final RouteSpecification routeSpecification = new RouteSpecification(cargo.origin(), newDestination,
				cargo.routeSpecification().arrivalDeadline());
		cargo.specifyNewRoute(routeSpecification);

		cargoRepository.store(cargo);
		logger.info("Changed destination for cargo " + trackingId + " to " + routeSpecification.destination());
	}

}
