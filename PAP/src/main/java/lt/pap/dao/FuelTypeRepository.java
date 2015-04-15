package lt.pap.dao;

import lt.pap.model.FuelType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    public FuelType findByName(String name);
}
