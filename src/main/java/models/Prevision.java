// Generated Model using mg.razherana.generator
// Happy Codingg!

package models;

import java.sql.SQLException;
import mg.razherana.lorm.annot.columns.Column;
import java.sql.Connection;
import mg.razherana.lorm.annot.relations.HasMany;
import mg.razherana.lorm.annot.general.Table;
import mg.razherana.lorm.Lorm;
import java.util.ArrayList;

@Table("webdyn1_prevision")
@HasMany(model = Depense.class, foreignKey = "prevision_id", relationName = "depenses")
public class Prevision extends Lorm<Prevision> {
  @Column(value = "montant", getter = "getMontant", setter = "setMontant")
  private double montant;

  @Column(value = "libelle", getter = "getLibelle", setter = "setLibelle")
  private String libelle;

  @Column(value = "id", primaryKey = true, getter = "getId", setter = "setId")
  private int id;

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ArrayList<Depense> getDepenses(Connection connection) throws SQLException {
    return hasMany("depenses", connection);
  }

  public double getTotalDepenses(Connection connection) throws SQLException {
    return getDepenses(connection).stream().mapToDouble((a) -> a.getMontant()).sum();
  }
}