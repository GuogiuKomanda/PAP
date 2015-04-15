package lt.pap.dao;

import lt.pap.model.Engine;
import lt.pap.model.Model;
import lt.pap.model.ModelEngine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelEngineRepository extends JpaRepository<ModelEngine, Long> {
    public ModelEngine findByModelAndEngine(Model model, Engine engine);

}