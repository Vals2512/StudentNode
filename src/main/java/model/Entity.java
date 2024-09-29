package model;

public abstract class Entity implements Comparable<Entity> {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Entity otherEntity) {
        return this.id.compareTo(otherEntity.getId());
    }

}
