package se.citerus.dddsample.infrastructure.persistence.hibernate;

import org.springframework.stereotype.Repository;

import se.citerus.dddsample.domain.model.location.entity.Location;
import se.citerus.dddsample.domain.model.location.repository.LocationRepository;
import se.citerus.dddsample.domain.model.location.vo.UnLocode;

import java.util.List;

@Repository
public class LocationRepositoryHibernate extends HibernateRepository implements LocationRepository {

	public Location find(final UnLocode unLocode) {
		return (Location) getSession().createQuery("from Location where unLocode = ?").setParameter(0, unLocode)
				.uniqueResult();
	}

	public List<Location> findAll() {
		return getSession().createQuery("from Location", Location.class).list();
	}

}
