package se.citerus.dddsample.infrastructure.persistence.hibernate;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import se.citerus.dddsample.domain.model.cargo.vo.TrackingId;
import se.citerus.dddsample.domain.model.handling.HandlingEvent;
import se.citerus.dddsample.domain.model.handling.HandlingEventRepository;
import se.citerus.dddsample.domain.model.handling.HandlingHistory;

/**
 * Hibernate implementation of HandlingEventRepository.
 *
 */
@Repository
public class HandlingEventRepositoryHibernate extends HibernateRepository implements HandlingEventRepository {

	@Override
	public void store(final HandlingEvent event) {
		getSession().save(event);
	}

	@Override
	public HandlingHistory lookupHandlingHistoryOfCargo(final TrackingId trackingId) {
		Collection<HandlingEvent> list = getSession()
				.createQuery("from HandlingEvent where cargo.trackingId = :tid", HandlingEvent.class)
				.setParameter("tid", trackingId).list();
		return new HandlingHistory(list);
	}

}
