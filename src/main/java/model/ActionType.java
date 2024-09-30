package model;

/**
 * Enum representing the action types that can be performed when adding a
 * student.
 */
public enum ActionType {

    /**
     * Makes reference to add node sorted
     */
    ADD,
    /**
     * Makes reference to add node at the begining
     */
    ADD_FIRST,
    /**
     * Makes reference to add node at the end
     */
    ADD_LAST,
    /**
     * Makes reference to add node before another
     */
    ADD_BEFORE,
    /**
     * Makes reference to add node after another
     */
    ADD_AFTER
}
