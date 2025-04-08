package models;

import java.sql.SQLException;

import mg.razherana.lorm.annot.columns.Column;
import java.sql.Connection;
import mg.razherana.lorm.annot.relations.BelongsTo;
import mg.razherana.lorm.annot.general.Table;
import mg.razherana.lorm.annot.columns.ForeignColumn;
import mg.razherana.lorm.Lorm;

@Table("webdyn1_depense")
@BelongsTo(model = Prevision.class, foreignKey = "prevision_id", relationName = "prevision")
public class Depense extends Lorm<Depense> {
  @Column(value = "created_at", getter = "getCreatedAt", setter = "setCreatedAt")
  private java.time.LocalDateTime createdAt;

  @Column(value = "montant", getter = "getMontant", setter = "setMontant")
  private double montant;

  @Column(value = "id", primaryKey = true, getter = "getId", setter = "setId")
  private int id;

  @Column(value = "prevision_id", getter = "getPrevisionid", setter = "setPrevisionid")
  @ForeignColumn(name = "id", model = Prevision.class)
  private int previsionId;

  @Column(value = "libelle", getter = "getLibelle", setter = "setLibelle")
  private String libelle;

  public java.time.LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.time.LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPrevisionid() {
    return previsionId;
  }

  public void setPrevisionid(int previsionId) {
    this.previsionId = previsionId;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public Prevision getPrevision(Connection connection) throws SQLException {
    return belongsTo("prevision", connection);
  }
}