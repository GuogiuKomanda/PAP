package lt.pap.dao;

import lt.pap.model.Part;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {

}
