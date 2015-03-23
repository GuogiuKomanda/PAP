package lt.pap.service;

import lt.pap.dao.EngineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EngineService
{
    @Autowired
    private EngineRepository engineRepository;
}
