package repository;

import interfaces.repository.IConfigurationRepository;
import entities.Configuration;
// import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Vector;

public class ConfigurationRepository extends RepositoryBase<Configuration> implements IConfigurationRepository {

    private Vector<Configuration> configurations;

    public ConfigurationRepository()
    {
        configurations = this.loadData();
    }


    public Configuration update(Configuration configuration)
    {
        if (configurations == null || configurations.isEmpty())
            return null;

        Configuration configurationResult = configurations.stream().filter(cnfg ->
                cnfg.getConfigKey() == configuration.getConfigKey()).findFirst().orElse(null);

        if (configurationResult == null)
            return null;

        configurations.remove(configurationResult);

        boolean result = configurations.add(configuration);

        if (result) {
            this.saveData(configurations);
            return configuration;
        } else {
            return null;
        }
    }


    public Configuration fetchConfigurationByName(String configkey)
    {
        if (configurations == null || configurations.isEmpty())
            return null;

        Configuration configurationResult = configurations.stream().filter(cnfg ->
                cnfg.getConfigKey().equals(configkey)).findFirst().orElse(null);

        if (configurationResult == null)
            return null;

        return configurationResult;
    }

    public List<Configuration> getConfigurations()
    {
        return configurations;
    }

    @Override
    public Configuration insert(Configuration configuration) {
        // lin because err : throw new NotImplementedException();
        return null;
    }
}
