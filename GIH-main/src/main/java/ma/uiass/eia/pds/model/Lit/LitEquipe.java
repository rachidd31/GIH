package ma.uiass.eia.pds.model.Lit;

import ma.uiass.eia.pds.model.Lit.Lit;
import ma.uiass.eia.pds.model.dm.Dm;
import ma.uiass.eia.pds.model.espace.Espace;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_lit_equipe")
public class LitEquipe extends Lit {
    @ManyToOne
    @JoinColumn(name = "espace_id")
    Espace espace;
    @OneToMany(mappedBy = "lit")
    List<Dm> lstDm = new ArrayList<>();
    public LitEquipe(){}

    public LitEquipe(int numero, EtatLit etat, TypeLit type, Espace espace, List<Dm> lstDm) {
        super(numero, etat, type);
        this.espace = espace;
        this.lstDm = lstDm;
    }

    public LitEquipe(EtatLit etat, TypeLit type, Espace espace, List<Dm> lstDm) {
        super(etat, type);
        this.espace = espace;
        this.lstDm = lstDm;
    }

    public Espace getEspace() {
        return espace;
    }

    public void setEspace(Espace espace) {
        this.espace = espace;
    }

    public List<Dm> getLstDm() {
        return lstDm;
    }

    public void setLstDm(List<Dm> lstDm) {
        this.lstDm = lstDm;
    }
}
