package Modele.Ressources;

import android.content.Context;

public class Ressources {

    public static String getNameOfRessource(Context context, int id){
        return context.getResources().getString(id);
    }
}
