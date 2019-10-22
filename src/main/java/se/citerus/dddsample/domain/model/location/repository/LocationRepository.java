package se.citerus.dddsample.domain.model.location.repository;

import java.util.List;

import se.citerus.dddsample.domain.model.location.entity.Location;
import se.citerus.dddsample.domain.model.location.vo.UnLocode;

public interface LocationRepository {

	/**
	 * Finds a location using given unlocode.
	 *
	 * @param unLocode UNLocode.
	 * @return Location.
	 */
	Location find(UnLocode unLocode);

	/**
	 * Finds all locations.
	 *
	 * @return All locations.
	 */
	List<Location> findAll();

}
