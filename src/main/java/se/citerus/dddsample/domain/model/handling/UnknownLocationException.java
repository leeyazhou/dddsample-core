package se.citerus.dddsample.domain.model.handling;

import se.citerus.dddsample.domain.model.location.vo.UnLocode;

public class UnknownLocationException extends CannotCreateHandlingEventException {

	private static final long serialVersionUID = 1L;
	
	private final UnLocode unlocode;

	public UnknownLocationException(final UnLocode unlocode) {
		this.unlocode = unlocode;
	}

	@Override
	public String getMessage() {
		return "No location with UN locode " + unlocode.idString() + " exists in the system";
	}
}
