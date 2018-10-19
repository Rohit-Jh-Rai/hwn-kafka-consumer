package nl.rws.hwn.statistics.configuration;

/**
 * Kafka topic names enum
 */
public enum TopicNames {
    VariableMessageSign("VariableMessageSign"),
    SpeedAndFlow("SpeedAndFlow"),
    VehicleDetection("VehicleDetection");

    private String topicName;

    TopicNames(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
