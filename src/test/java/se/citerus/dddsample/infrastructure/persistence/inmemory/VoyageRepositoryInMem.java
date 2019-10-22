package se.citerus.dddsample.infrastructure.persistence.inmemory;

import se.citerus.dddsample.domain.model.voyage.SampleVoyages;
import se.citerus.dddsample.domain.model.voyage.entity.Voyage;
import se.citerus.dddsample.domain.model.voyage.repository.VoyageRepository;
import se.citerus.dddsample.domain.model.voyage.vo.VoyageNumber;

public final class VoyageRepositoryInMem implements VoyageRepository {

  public Voyage find(VoyageNumber voyageNumber) {
    return SampleVoyages.lookup(voyageNumber);
  }
  
}
