package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.dm.DM;
import ma.uiass.eia.pds.model.dm.TypeDM;

import java.util.List;

public interface DmManager {
    void saveDM(String nom, int quantité, TypeDM typeDM);
    void saveTypeDM(String nom);
    TypeDM getTypeDmByName(String typenom);
    List<TypeDM> getAllTypeDM();
    List<DM> getAllDMByType(String typeDM);
}
