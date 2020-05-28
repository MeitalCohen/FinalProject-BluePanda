package interfaces.business;

import entities.Configuration;

import java.util.List;
import java.util.Vector;

public interface IConfigurationManager {

    List<Configuration> getConfigurations();

    Configuration getConfiguration(String configKey);

    Configuration updateConfiguration(Configuration configuration);
}
