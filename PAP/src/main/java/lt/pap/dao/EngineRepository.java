package lt.pap.dao;

import lt.pap.model.Engine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Long> {

}