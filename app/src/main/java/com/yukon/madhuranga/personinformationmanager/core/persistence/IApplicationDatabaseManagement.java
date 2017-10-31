package com.yukon.madhuranga.personinformationmanager.core.persistence;

/**
 * Defines the contract which should be implemented by database management entities
 * declared for the application.
 */
public interface IApplicationDatabaseManagement {
    /**
     * Gets the database associated in the current instance.
     *
     * @return A reference to the database as an Object.
     */
    Object getDatabase();
}
