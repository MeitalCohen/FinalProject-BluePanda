package entities;

public class Configuration extends Entity {

    private int configId;
    private String ConfigKey;
    private String ConfigValue;
    private String Description;

    public Configuration() {

    }

    public Configuration( String configKey, String configValue, String description) {
        this.configId = Entity.id;
        ConfigKey = configKey;
        ConfigValue = configValue;
        Description = description;
        Entity.id++;
    }

    public Configuration(int configId, String configKey, String configValue, String description) {
        this.configId = configId;
        ConfigKey = configKey;
        ConfigValue = configValue;
        Description = description;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "configId=" + configId +
                ", ConfigKey='" + ConfigKey + '\'' +
                ", ConfigValue='" + ConfigValue + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public String getConfigKey() {
        return ConfigKey;
    }

    public void setConfigKey(String configKey) {
        ConfigKey = configKey;
    }

    public String getConfigValue() {
        return ConfigValue;
    }

    public void setConfigValue(String configValue) {
        ConfigValue = configValue;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }
}
