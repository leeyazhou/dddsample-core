package se.citerus.dddsample.domain.model.voyage.repository;

import se.citerus.dddsample.domain.model.voyage.entity.Voyage;
import se.citerus.dddsample.domain.model.voyage.vo.VoyageNumber;

public interface VoyageRepository {

	/**
	 * Finds a voyage using voyage number.
	 *
	 * @param voyageNumber voyage number
	 * @return The voyage, or null if not found.
	 */
	Voyage find(VoyageNumber voyageNumber);

}
