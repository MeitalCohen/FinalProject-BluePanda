package managers;

import entities.Configuration;
import interfaces.business.IConfigurationManager;
import interfaces.repository.IConfigurationRepository;

import java.util.List;

public class ConfigurationManager implements IConfigurationManager {

    private IConfigurationRepository _configurationRepository;

    public ConfigurationManager(IConfigurationRepository configurationRepository)
    {
        _configurationRepository = configurationRepository;
    }

    @Override
    public List<Configuration> getConfigurations() {
        return _configurationRepository.getConfigurations();
    }

    @Override
    public Configuration getConfiguration(String configKey) {
        return _configurationRepository.fetchConfigurationByName(configKey);
    }

    @Override
    public Configuration updateConfiguration(Configuration configuration) {
        return _configurationRepository.update(configuration);
    }
}
