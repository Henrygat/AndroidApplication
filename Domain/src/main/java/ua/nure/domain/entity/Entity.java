package ua.nure.domain.entity;

public abstract class Entity {
    private Integer identifier;

    public Entity() {
        identifier = -1;
    }

    public Entity(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = this.identifier.equals(-1) ? identifier : this.identifier;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Entity && ((Entity) object).identifier.equals(identifier);
    }

    @Override
    public String toString() {
        return "Entity{" + "identifier=" + identifier + '}';
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}