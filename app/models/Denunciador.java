package models;

import models.dao.GenericDAOImpl;

/**
 * Created by axius on 19/11/15.
 */
public class Denunciador {
    private int max;
    private String userName = "";
    private GenericDAOImpl dao;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GenericDAOImpl getDao() {
        return dao;
    }

    public void setDao(GenericDAOImpl dao) {
        this.dao = dao;
    }

    public Denunciador(int max, String userName, GenericDAOImpl dao) {
        this.max = max;
        this.userName = userName;
        this.dao = dao;
    }

    public boolean denuncia(Denunciavel dica){
        boolean ret = false;
        if (!dica.wasFlaggedByUser(userName)) {
            dica.addUsuarioFlag(userName);
            dica.incrementaFlag();

            if (dica.getFlag() == max) {
                try {
                    dao.removeById(Class.forName(dica.getClass().getName()), dica.getId());
                }catch (Exception ex){

                }

            } else {
                dao.merge(dica);
            }
            ret = true;
        } else {
            ret= false;
        }

        dao.flush();
        return ret;
    }
}
