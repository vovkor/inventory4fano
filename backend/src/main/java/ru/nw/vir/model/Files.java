package ru.nw.vir.model;

import java.util.Date;
public class Files {

    int id;
    String name;
	Date date_upload;
	int owner_id;
	String owner;
	String comment;
	
	
	public Files(int id){
			this.id = id;
	}
	
	
    public Files(int id,
				String name,
				Date date_upload,
				int owner_id,
				String owner,
				String comment
				)
	{
        this.id = id;
        this.name = name;
		this.date_upload = date_upload;
		this.owner_id = owner_id;
		this.owner = owner;
		this.comment = comment;
		
    }

    //getters and setters and toString...
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name'" + name + '\'' +
                ", date_upload'" + date_upload + '\'' +
                ", owner_id" + owner_id +
                '}';
    }

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getDate_upload() {
			return date_upload;
		}
		public void setDate_upload(Date date_upload) {
			this.date_upload = date_upload;
		}
		public int getOwner_id() {
			return owner_id;
		}
		public void setOwner_id(int owner_id) {
			this.owner_id = owner_id;
		}
		public String getOwner() {
			return owner;
		}
		public void setOwner(String owner) {
			this.owner = owner;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		

}