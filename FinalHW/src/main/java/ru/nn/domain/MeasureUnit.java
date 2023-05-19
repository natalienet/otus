package ru.nn.domain;

abstract public class MeasureUnit {
    private final String singular;
    private final String plural;
    private final String singularAndGenitive;
    private final Gender gender;

    public MeasureUnit(String singular, String singularAndGenitive, String plural, Gender gender) {
        this.singular = singular;
        this.singularAndGenitive = singularAndGenitive;
        this.plural = plural;
        this.gender = gender;
    }

    public String getSingular() {
        return singular;
    }

    public String getPlural() {
        return plural;
    }

    public String getSingularAndGenitive() {
        return singularAndGenitive;
    }

    public Gender getGender() {
        return gender;
    }
}
