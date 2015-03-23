package lt.pap.dao;

import lt.pap.model.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    
}
