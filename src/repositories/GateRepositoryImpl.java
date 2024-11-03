package repositories;

import models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepositoryImpl implements GateRepository{
    private Map<Long , Gate> gateDb = new HashMap<>();

    public Optional<Gate> findById(long gateId){
        if(gateDb.containsKey(gateId)){
            return Optional.of(gateDb.get(gateId));
        }
        return Optional.empty();
    }

    public Gate save(Gate gate){
        gateDb.put(gate.getId(), gate);
        return gate;
    }
}