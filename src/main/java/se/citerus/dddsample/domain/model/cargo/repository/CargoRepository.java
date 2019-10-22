package se.citerus.dddsample.domain.model.cargo.repository;

import java.util.List;

import se.citerus.dddsample.domain.model.cargo.entity.Cargo;
import se.citerus.dddsample.domain.model.cargo.vo.TrackingId;

public interface CargoRepository {

	/**
	 * Finds a cargo using given id.
	 *
	 * @param trackingId Id
	 * @return Cargo if found, else {@code null}
	 */
	Cargo find(TrackingId trackingId);

	/**
	 * Finds all cargo.
	 *
	 * @return All cargo.
	 */
	List<Cargo> findAll();

	/**
	 * Saves given cargo.
	 *
	 * @param cargo cargo to save
	 */
	void store(Cargo cargo);

	/**
	 * @return A unique, generated tracking Id.
	 */
	TrackingId nextTrackingId();

}
