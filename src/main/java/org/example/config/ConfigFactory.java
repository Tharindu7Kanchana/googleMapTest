package org.example.config;

public class ConfigFactory {
    public static Constants instance;

    private ConfigFactory() {
    }

    public static Constants getInstance() {
        if (null == instance) {
            instance = new Constants() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }

                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(ConfigFactory.getInstance().PLATFORM);
    }
}
