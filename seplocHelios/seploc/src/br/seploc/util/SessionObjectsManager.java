package br.seploc.util;

import javax.faces.context.FacesContext;

public class SessionObjectsManager {
    public static Object recuperaObjetoSessao(String chave) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(chave);
    }

     
    public static void adicionaObjetoSessao(String chave, Object valor) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(chave, valor);
    }

    public static void removeObjetoSessao(String chave ) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(chave) ;
    }
}
