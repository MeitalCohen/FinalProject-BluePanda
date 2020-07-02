package services.requests;

import entities.Configuration;

import java.util.Vector;

public class UpdateConfigurationRequest extends RequestBase{
    private Vector<Configuration> configuration;

    public UpdateConfigurationRequest(Vector<Configuration> configuration) {
        this.configuration = configuration;
    }

    public Vector<Configuration> getConfiguration() {
        return configuration;
    }
}
