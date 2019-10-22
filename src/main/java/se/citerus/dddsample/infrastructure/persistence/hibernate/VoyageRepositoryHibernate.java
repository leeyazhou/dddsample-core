package se.citerus.dddsample.infrastructure.persistence.hibernate;

import org.springframework.stereotype.Repository;

import se.citerus.dddsample.domain.model.voyage.entity.Voyage;
import se.citerus.dddsample.domain.model.voyage.repository.VoyageRepository;
import se.citerus.dddsample.domain.model.voyage.vo.VoyageNumber;

/**
 * Hibernate implementation of CarrierMovementRepository.
 */
@Repository
public class VoyageRepositoryHibernate extends HibernateRepository implements VoyageRepository {

	public Voyage find(final VoyageNumber voyageNumber) {
		return (Voyage) getSession().createQuery("from Voyage where voyageNumber = :vn")
				.setParameter("vn", voyageNumber).uniqueResult();
	}

}
