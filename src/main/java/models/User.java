// Generated Model using mg.razherana.generator
// Happy Codingg!

package models;

import mg.razherana.lorm.annot.columns.Column;
import mg.razherana.lorm.annot.general.Table;
import mg.razherana.lorm.Lorm;

@Table("webdyn1_user")
public class User extends Lorm<User> { 
	@Column(value = "password", getter = "getPassword", setter = "setPassword")
	private String password;

	@Column(value = "id", primaryKey = true, getter = "getId", setter = "setId")
	private int id;

	@Column(value = "username", getter = "getUsername", setter = "setUsername")
	private String username;

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }
}