package model;

/**
 * Abstract class representing a generic entity.
 * This class provides a basic implementation for entities with an ID.
 * It implements the Comparable interface to allow entities to be compared based
 * on their IDs.
 */
public abstract class Entity implements Comparable<Entity> {

    /**
     * The unique identifier for the entity.
     * This ID is used to distinguish each instance in the system.
     */
    protected String id;


    /**
     * Gets the ID of this entity.
     * 
     * @return the ID of this entity
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of this entity.
     * 
     * @param id the new ID for this entity
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Compares this entity with another entity based on their IDs.
     * 
     * @param otherEntity the entity to compare with
     * @return a negative integer if this entity's ID is less than the other
     *         entity's ID,
     *         zero if the IDs are equal, and a positive integer if this entity's ID
     *         is greater
     */

    @Override
    public int compareTo(Entity otherEntity) {
        return this.id.compareTo(otherEntity.getId());
    }

}
