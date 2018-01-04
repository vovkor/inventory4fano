package ru.nw.vir.model;

import java.util.Date;
public class Acc {

    int id;
    String accenumb;
	String collmunb;
	String collcode;
	String expedition;
	String cropname;
	String genus;
	String species;
	String spauthor;
	String subtaxa;
	String subtauthor;
	String accename;
	String accename_rus;
	String acqdate;
	String origcty;
	String collsite;
	String collsite_rus;
	String latitude;
	String longitude;
	String elevation;
	String colldate;
	String bredcode;
	String sampstat;
	String ancest;
	String ancest_rus;
	String collsrc;
	String doncty;
	String donorcode;
	String donornumb;
	String othernumb;
	String duplsite;
	String storage;
	String lifform;
	String intrnumb;
	String remarks;
	String owner;	
    Date created_date;
	
	public Acc(int id){
			this.id = id;
	}
	
	
    public Acc(int id,
				String accenumb,
				String collmunb,
				String collcode,
				String expedition,
				String cropname,
				String genus,
				String species,
				String spauthor,
				String subtaxa,
				String subtauthor,
				String accename,
				String accename_rus,
				String acqdate,
				String origcty,
				String collsite,
				String collsite_rus,
				String latitude,
				String longitude,
				String elevation,
				String colldate,
				String bredcode,
				String sampstat,
				String ancest,
				String ancest_rus,
				String collsrc,
				String doncty,
				String donorcode,
				String donornumb,
				String othernumb,
				String duplsite,
				String storage,
				String lifform,
				String intrnumb,
				String remarks,
				String owner,
				Date created_date)
	{
        this.id = id;
        this.accenumb = accenumb;
		this.collmunb = collmunb;
		this.collcode = collcode;
		this.expedition = expedition;
		this.cropname = cropname;
		this.genus = genus;
		this.species = species;
		this.spauthor = spauthor;
		this.subtaxa = subtaxa;
		this.subtauthor = subtauthor;
		this.accename = accename;
		this.accename_rus = accename_rus;
		this.acqdate = acqdate;
		this.origcty = origcty;
		this.collsite = collsite;
		this.collsite_rus = collsite_rus;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.colldate = colldate;
		this.bredcode = bredcode;
		this.sampstat = sampstat;
		this.ancest = ancest;
		this.ancest_rus = ancest_rus;
		this.collsrc = collsrc;
		this.doncty = doncty;
		this.donorcode = donorcode;
		this.donornumb = donornumb;
		this.othernumb = othernumb;
		this.duplsite = duplsite;
		this.storage = storage;
		this.lifform = lifform;
		this.intrnumb = intrnumb;
		this.remarks = remarks;
		this.owner = owner;			
        this.created_date = created_date;
    }

    //getters and setters and toString...
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accenumb='" + accenumb + '\'' +
                ", genus='" + genus + '\'' +
                ", created_date=" + created_date +
                '}';
    }

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getAccenumb() {
			return accenumb;
		}
		public void setAccenumb(String accenumb) {
			this.accenumb = accenumb;
		}
		public String getCollmunb() {
			return collmunb;
		}
		public void setCollmunb(String collmunb) {
			this.collmunb = collmunb;
		}
		public String getCollcode() {
			return collcode;
		}
		public void setCollcode(String collcode) {
			this.collcode = collcode;
		}
		public String getExpedition() {
			return expedition;
		}
		public void setExpedition(String expedition) {
			this.expedition = expedition;
		}
		public String getCropname() {
			return cropname;
		}
		public void setCropname(String cropname) {
			this.cropname = cropname;
		}		
		public String getGenus() {
			return genus;
		}		
		public void setGenus(String genus) {
			this.genus = genus;
		}
		public String getSpecies() {
			return species;
		}
		public void setSpecies(String species) {
			this.species = species;
		}
		public String getSpauthor() {
			return spauthor;
		}
		public void setSpauthor(String spauthor) {
			this.spauthor = spauthor;
		}
		public String getSubtaxa() {
			return subtaxa;
		}
		public void setSubtaxa(String subtaxa) {
			this.subtaxa = subtaxa;
		}
		public String getSubtauthor() {
			return subtauthor;
		}
		public void setSubtauthor(String subtauthor) {
			this.subtauthor = subtauthor;
		}
		public String getAccename() {
			return accename;
		}
		public void setAccename(String accename) {
			this.accename = accename;
		}
		public String getAccename_rus() {
			return accename_rus;
		}
		public void setAccename_rus(String accename_rus) {
			this.accename_rus = accename_rus;
		}
		public String getAcqdate() {
			return acqdate;
		}
		public void setAcqdate(String acqdate) {
			this.acqdate = acqdate;
		}
		public String getOrigcty() {
			return origcty;
		}
		public void setOrigcty(String origcty) {
			this.origcty = origcty;
		}
		public String getCollsite() {
			return collsite;
		}
		public void setCollsite(String collsite) {
			this.collsite = collsite;
		}
		public String getCollsite_rus() {
			return collsite_rus;
		}
		public void setCollsite_rus(String collsite_rus) {
			this.collsite_rus = collsite_rus;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getElevation() {
			return elevation;
		}
		public void setElevation(String elevation) {
			this.elevation = elevation;
		}
		public String getColldate() {
			return colldate;
		}
		public void setColldate(String colldate) {
			this.colldate = colldate;
		}
		public String getBredcode() {
			return bredcode;
		}
		public void setBredcode(String bredcode) {
			this.bredcode = bredcode;
		}
		public String getSampstat() {
			return sampstat;
		}
		public void setSampstat(String sampstat) {
			this.sampstat = sampstat;
		}
		public String getAncest() {
			return ancest;
		}
		public void setAncest(String ancest) {
			this.ancest = ancest;
		}
		public String getAncest_rus() {
			return ancest_rus;
		}
		public void setAncest_rus(String ancest_rus) {
			this.ancest_rus = ancest_rus;
		}
		public String getCollsrc() {
			return collsrc;
		}
		public void setCollsrc(String collsrc) {
			this.collsrc = collsrc;
		}
		public String getDoncty() {
			return doncty;
		}
		public void setDoncty(String doncty) {
			this.doncty = doncty;
		}
		public String getDonorcode() {
			return donorcode;
		}
		public void setDonorcode(String donorcode) {
			this.donorcode = donorcode;
		}
		public String getDonornumb() {
			return donornumb;
		}
		public void setDonornumb(String donornumb) {
			this.donornumb = donornumb;
		}
		public String getOthernumb() {
			return othernumb;
		}
		public void setOthernumb(String othernumb) {
			this.othernumb = othernumb;
		}
		public String getDuplsite() {
			return duplsite;
		}
		public void setDuplsite(String duplsite) {
			this.duplsite = duplsite;
		}
		public String getStorage() {
			return storage;
		}
		public void setStorage(String storage) {
			this.storage = storage;
		}
		public String getLifform() {
			return lifform;
		}
		public void setLifform(String lifform) {
			this.lifform = lifform;
		}
		public String getIntrnumb() {
			return intrnumb;
		}
		public void setIntrnumb(String intrnumb) {
			this.intrnumb = intrnumb;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getOwner() {
			return owner;
		}
		public void setOwner(String owner) {
			this.owner = owner;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}
}