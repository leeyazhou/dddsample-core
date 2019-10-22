package se.citerus.dddsample.domain.model.handling;

import se.citerus.dddsample.domain.model.cargo.vo.TrackingId;

/**
 * Thrown when trying to register an event with an unknown tracking id.
 */
public final class UnknownCargoException extends CannotCreateHandlingEventException {

	private static final long serialVersionUID = 1L;
	
	private final TrackingId trackingId;

	/**
	 * @param trackingId cargo tracking id
	 */
	public UnknownCargoException(final TrackingId trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return "No cargo with tracking id " + trackingId.idString() + " exists in the system";
	}
}
