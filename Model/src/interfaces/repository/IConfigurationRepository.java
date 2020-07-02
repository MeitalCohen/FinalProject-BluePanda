package interfaces.repository;

import entities.Configuration;
import exceptions.GeneralErrorException;

import java.util.List;
import java.util.Vector;

public interface IConfigurationRepository extends IRepository<Configuration>{

    Vector<Configuration> update(Vector<Configuration> configurations) throws GeneralErrorException;

    Configuration fetchConfigurationByName(String configkey);

    List<Configuration> getConfigurations();
}
