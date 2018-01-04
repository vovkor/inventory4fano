package ru.nw.vir.model;

import java.util.Date;
public class Account {

    int id;
    String username;
	String password;
	String fio;
	String casta;
	String depart;
	int boss;
	
	public Account(int id){
			this.id = id;
	}
	
	
    public Account(int id,
				String username,
				String password,
				String fio,
				String casta,
				String depart,
				int boss)
	{
        this.id = id;
        this.username = username;
		this.password = password;
		this.fio = fio;
		this.casta = casta;
		this.depart = depart;
		this.boss = boss;
    }

    //getters and setters and toString...
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username'" + username + '\'' +
                ", password'" + password + '\'' +
                ", fio" + fio +
                '}';
    }

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFio() {
			return fio;
		}
		public void setFio(String fio) {
			this.fio = fio;
		}
		public String getCasta() {
			return casta;
		}
		public void setCasta(String casta) {
			this.casta = casta;
		}
		public String getDepart() {
			return depart;
		}
		public void setDepart(String depart) {
			this.depart = depart;
		}
		public int getBoss() {
			return boss;
		}
		public void setBoss(int boss) {
			this.boss = boss;
		}

}