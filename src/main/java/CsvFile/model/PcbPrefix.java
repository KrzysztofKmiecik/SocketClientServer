package CsvFile.model;

public class PcbPrefix {
    private String prefix;

    public PcbPrefix() {
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "Prefixes{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
