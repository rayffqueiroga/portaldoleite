package models;

import models.dao.GenericDAOImpl;

/**
 * Created by axius on 19/11/15.
 */
public interface Denunciavel {
    public boolean wasFlaggedByUser(String user);
    public void addUsuarioFlag(String user);
    public void incrementaFlag();
    public int getFlag();
    public Long getId();
}
