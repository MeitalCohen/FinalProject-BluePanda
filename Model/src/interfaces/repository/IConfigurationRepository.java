package interfaces.repository;

import entities.Configuration;

import java.util.List;

public interface IConfigurationRepository {

    public Configuration update(Configuration configuration);

    public Configuration fetchConfigurationByName(String configkey);

    public List<Configuration> getConfigurations();
}
