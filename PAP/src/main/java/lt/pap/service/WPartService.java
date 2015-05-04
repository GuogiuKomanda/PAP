package lt.pap.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lt.pap.dao.WPartRepository;
import lt.pap.model.Engine;
import lt.pap.model.FuelType;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.model.ModelEngine;
import lt.pap.model.ModelGroup;
import lt.pap.model.WPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WPartService {
  @Autowired
  private WPartRepository WPartRepository;

  @PersistenceContext
  private EntityManager em;

  public List<WPart> findAll() {
    return WPartRepository.findAll();
  }

  public WPart findOne(Long id) {
    return WPartRepository.findOne(id);
  }

  public WPart save(WPart me) {
    return WPartRepository.save(me);
  }

  public List<WPart> findByFilters(String make) {
    return WPartRepository.findByFilters(make);
  }

  public List<WPart> findByFilters2(String makeId, List<Long> modelIds, List<Long> fuelTypeIds) {
    CriteriaBuilder cb = em.getCriteriaBuilder();

    CriteriaQuery<WPart> q = cb.createQuery(WPart.class);
    Root<WPart> parts = q.from(WPart.class);

    Path<ModelEngine> modelEngine = parts.get("modelEngine");
    Path<Model> model = modelEngine.get("model");
    Path<Engine> engine = modelEngine.get("engine");
    Path<FuelType> fuel = engine.get("fuelType");
    Path<ModelGroup> modelGroup = model.get("modelGroup");
    Path<Make> make = modelGroup.get("make");

//    ParameterExpression<String> p = cb.parameter(String.class);

    List<Predicate> predList = new ArrayList<Predicate>();
    // make not null
    if (makeId != null && !makeId.isEmpty()) {
      predList.add(cb.equal(make.get("id"), makeId));
      
    }
    //model list
    if (modelIds != null && !modelIds.isEmpty()) {
      predList.add(model.get("id").in(modelIds));
    }
    
    //fuel list
    if (fuelTypeIds != null && !fuelTypeIds.isEmpty()) {
      predList.add(fuel.get("id").in(fuelTypeIds));
    }
    
    Predicate[] predArray = new Predicate[predList.size()];
    predList.toArray(predArray);

    q.select(parts).where(predArray);

    TypedQuery<WPart> query = em.createQuery(q);
    List<WPart> results = query.getResultList();

    return results;
  }


}
